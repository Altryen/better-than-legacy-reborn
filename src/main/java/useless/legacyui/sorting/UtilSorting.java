package useless.legacyui.sorting;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import useless.legacyui.LegacyUI;

import java.util.ArrayList;
import java.util.List;

public class UtilSorting {
    public static boolean stackInClassList(final Iterable<Class<?>> classList, final ItemStack itemStack){

        if (itemStack.itemID < Blocks.blocksList.length){
            Block<?> b = Blocks.getBlock(itemStack.itemID);
            for (final Class<?> clazz : classList){
                if (Block.hasLogicClass(b, clazz)) {
                    return true;
                }
            }
        } else {
            Class<?> itemClass = itemStack.getItem().getClass();
            for (final Class<?> clazz : classList){
                if (clazz.isAssignableFrom(itemClass)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean stackInItemList(final Iterable<ItemStack> itemStackList, final ItemStack itemStack){
        for (final ItemStack stack : itemStackList){
            if (itemStack.itemID == stack.itemID && itemStack.getMetadata() == stack.getMetadata()){
                return true;
            }
        }
        return false;
    }
    public static boolean stackInKeywordList(final Iterable<String> keywordList, final ItemStack itemStack){
        for (final String keyword : keywordList){
            if (itemStack.getItem().getKey().contains(keyword)){
                return true;
            }
        }
        return false;
    }
    public static boolean recipeInRecipeList(final Iterable<? extends RecipeEntryCrafting<?, ?>> recipeList, final RecipeEntryCrafting<?,?> recipe){
        for (final RecipeEntryCrafting<?,?> groupRecipe : recipeList){
            if (groupRecipe.equals(recipe)){
                return true;
            }
        }
        return false;
    }
    public static void printRecipeList(final Iterable<? extends RecipeEntryCrafting<?, ?>> recipes){
        for (final RecipeEntryCrafting<?,?> recipe : recipes){
            LegacyUI.LOGGER.info("Output:{}", recipe.getOutput());
        }
    }

    /**
     * Returns a material-tier priority (lower = earlier) for tool/armor items so they can be
     * displayed Wood/Leather -> Stone/Chainmail -> Iron -> Gold -> Diamond, matching the
     * original console-edition ordering. Returns Integer.MAX_VALUE for anything that isn't a
     * recognizable tiered tool/armor item, so non-tool/armor items are left completely untouched.
     */
    public static int getMaterialTierPriority(final ItemStack itemStack){
        if (itemStack == null || itemStack.getItem() == null){
            return Integer.MAX_VALUE;
        }
        final String key = itemStack.getItem().getKey();
        if (key == null || !(key.startsWith("item.tool.") || key.startsWith("item.armor."))){
            return Integer.MAX_VALUE;
        }
        if (key.endsWith(".wood") || key.endsWith(".leather")){
            return 0;
        }
        if (key.endsWith(".stone") || key.endsWith(".chainmail")){
            return 1;
        }
        if (key.endsWith(".iron")){
            return 2;
        }
        if (key.endsWith(".gold")){
            return 3;
        }
        if (key.endsWith(".diamond")){
            return 4;
        }
        if (key.endsWith(".steel")){
            return 5;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Re-orders only the recognizable tiered tool/armor items within the given list by material
     * tier (wood/leather first, then stone/chainmail, iron, gold, diamond, steel), leaving every
     * other item exactly where it was.
     */
    public static void sortItemStacksByMaterialTierInPlace(final List<ItemStack> items){
        final List<Integer> tieredIndices = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (getMaterialTierPriority(items.get(i)) != Integer.MAX_VALUE) {
                tieredIndices.add(i);
            }
        }
        if (tieredIndices.size() < 2) {
            return;
        }
        final List<ItemStack> tieredItems = new ArrayList<>();
        for (final int idx : tieredIndices) {
            tieredItems.add(items.get(idx));
        }
        tieredItems.sort((a, b) -> Integer.compare(getMaterialTierPriority(a), getMaterialTierPriority(b)));
        for (int i = 0; i < tieredIndices.size(); i++) {
            items.set(tieredIndices.get(i), tieredItems.get(i));
        }
    }

    /**
     * Same as {@link #sortItemStacksByMaterialTierInPlace(List)} but for a list of crafting
     * recipes, comparing by each recipe's output item.
     */
    public static void sortRecipesByMaterialTierInPlace(final List<RecipeEntryCrafting<?,?>> recipes){
        final List<Integer> tieredIndices = new ArrayList<>();
        for (int i = 0; i < recipes.size(); i++) {
            if (getMaterialTierPriority((ItemStack) recipes.get(i).getOutput()) != Integer.MAX_VALUE) {
                tieredIndices.add(i);
            }
        }
        if (tieredIndices.size() < 2) {
            if (!recipes.isEmpty()) {
                final ItemStack firstOut = (ItemStack) recipes.get(0).getOutput();
                LegacyUI.LOGGER.info("[tierSort] group of {} recipes, only {} tiered (key of first item: '{}'), skipping reorder", recipes.size(), tieredIndices.size(), firstOut.getItem().getKey());
            }
            return;
        }
        final List<RecipeEntryCrafting<?,?>> tieredRecipes = new ArrayList<>();
        for (final int idx : tieredIndices) {
            tieredRecipes.add(recipes.get(idx));
        }
        LegacyUI.LOGGER.info("[tierSort] BEFORE: {}", tieredRecipes.stream().map(r -> ((ItemStack) r.getOutput()).getItem().getKey()).collect(java.util.stream.Collectors.joining(", ")));
        tieredRecipes.sort((a, b) -> Integer.compare(getMaterialTierPriority((ItemStack) a.getOutput()), getMaterialTierPriority((ItemStack) b.getOutput())));
        LegacyUI.LOGGER.info("[tierSort] AFTER:  {}", tieredRecipes.stream().map(r -> ((ItemStack) r.getOutput()).getItem().getKey()).collect(java.util.stream.Collectors.joining(", ")));
        for (int i = 0; i < tieredIndices.size(); i++) {
            recipes.set(tieredIndices.get(i), tieredRecipes.get(i));
        }
    }

    /**
     * Moves any items in {@code items} that also appear in {@code priorityOrder} to the front,
     * in exactly the order given by {@code priorityOrder}. Everything else keeps its existing
     * relative order after the prioritized items. Used so an explicit .addItem(...) call order
     * (e.g. plain Oak Planks before the painted variants) is respected even though the
     * underlying recipe/creative-item registries may list them in a different order.
     */
    public static void reorderItemStacksByExplicitPriority(final List<ItemStack> items, final List<ItemStack> priorityOrder){
        if (priorityOrder.isEmpty()) {
            return;
        }
        final List<ItemStack> front = new ArrayList<>();
        for (final ItemStack want : priorityOrder) {
            for (int i = 0; i < items.size(); i++) {
                final ItemStack candidate = items.get(i);
                if (candidate.itemID == want.itemID && candidate.getMetadata() == want.getMetadata()) {
                    front.add(items.remove(i));
                    break;
                }
            }
        }
        items.addAll(0, front);
    }

    /**
     * Same as {@link #reorderItemStacksByExplicitPriority(List, List)} but for a list of crafting
     * recipes, matching by each recipe's output item against the priority list.
     */
    public static void reorderRecipesByExplicitPriority(final List<RecipeEntryCrafting<?,?>> recipes, final List<ItemStack> priorityOrder){
        if (priorityOrder.isEmpty()) {
            return;
        }
        final List<RecipeEntryCrafting<?,?>> front = new ArrayList<>();
        for (final ItemStack want : priorityOrder) {
            for (int i = 0; i < recipes.size(); i++) {
                final ItemStack candidate = (ItemStack) recipes.get(i).getOutput();
                if (candidate.itemID == want.itemID && candidate.getMetadata() == want.getMetadata()) {
                    front.add(recipes.remove(i));
                    break;
                }
            }
        }
        recipes.addAll(0, front);
    }
}
