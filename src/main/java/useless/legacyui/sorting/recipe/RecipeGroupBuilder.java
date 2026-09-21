package useless.legacyui.sorting.recipe;

import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShaped;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShapeless;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryDyeing;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import useless.legacyui.LegacyUI;
import useless.legacyui.helper.InventoryHelper;
import net.minecraft.client.gui.guidebook.SlotGuidebook;
import useless.legacyui.sorting.UtilSorting;

import java.util.ArrayList;
import java.util.List;

public class RecipeGroupBuilder{
    private static final List<RecipeEntryCrafting<?,?>> allRecipes = new ArrayList<>(Registries.RECIPES.getAllCraftingRecipes());
    private static final List<RecipeEntryCrafting<?,?>> unusedRecipes = new ArrayList<>(allRecipes);
    private Boolean isDebug = false;
    private final List<Class<?>> inclusiveClassList = new ArrayList<>();
    private final List<ItemStack> inclusiveItemList = new ArrayList<>();
    private final List<String> inclusiveKeywordList = new ArrayList<>();
    private final List<Class<?>> exclusiveClassList = new ArrayList<>();
    private final List<ItemStack> exclusiveItemList = new ArrayList<>();
    private final List<String> exclusiveKeywordList = new ArrayList<>();
    private final List<ItemStack> excludeItemList = new ArrayList<>();
    private final List<String> excludeKeywordList = new ArrayList<>();
    private final List<Class<?>> excludeClassList = new ArrayList<>();
    public RecipeGroupBuilder excludeClass(Class clazz){
        if (isDebug){
            LegacyUI.LOGGER.info(clazz.getName());
        }
        excludeClassList.add(clazz);
        return this;
    }
    public RecipeGroupBuilder excludeKeyword(String keyword){
        if (isDebug){
            LegacyUI.LOGGER.info(keyword);
        }
        excludeKeywordList.add(keyword);
        return this;
    }
    public RecipeGroupBuilder excludeItem(Item item){
        return excludeItem(new ItemStack(item));
    }
    public RecipeGroupBuilder excludeItem(ItemStack stack){
        if (isDebug){
            LegacyUI.LOGGER.info(stack.toString());
        }
        excludeItemList.add(stack);
        return this;
    }
    public RecipeGroupBuilder addKeyword(String keyword){
        return addKeyword(keyword, false);
    }
    public RecipeGroupBuilder addKeyword(String keyword, boolean isInclusive){
        if (isDebug){
            LegacyUI.LOGGER.info(keyword);
        }

        if (isInclusive){
            inclusiveKeywordList.add(keyword);
        } else {
            exclusiveKeywordList.add(keyword);
        }

        return this;
    }
    public RecipeGroupBuilder addItemsWithMetaRange(Item item, int metaStart, int metaRange, boolean isInclusive){
        for (int i = 0; i < metaRange; i++) {
            this.addItem(item, metaStart+i, isInclusive);
        }
        return this;
    }
    public RecipeGroupBuilder addItem(Block block){
        return addItem(block.asItem(), false);
    }
    public RecipeGroupBuilder addItem(Block block, boolean isInclusive){
        return addItem(block.asItem(), isInclusive);
    }
    public RecipeGroupBuilder addItem(int id, int meta){
        return addItem(new ItemStack(id, 1, meta), false);
    }
    public RecipeGroupBuilder addItem(int id, int meta, boolean isInclusive){
        return addItem(new ItemStack(id, 1, meta), isInclusive);
    }
    public RecipeGroupBuilder addItem(Item item, int meta){
        return addItem(new ItemStack(item, 1, meta), false);
    }
    public RecipeGroupBuilder addItem(Item item, int meta, boolean isInclusive){
        return addItem(new ItemStack(item, 1, meta), isInclusive);
    }
    public RecipeGroupBuilder addItem(Item item, boolean isInclusive){
        return addItem(new ItemStack(item), isInclusive);
    }
    public RecipeGroupBuilder addItem(Item item){
        return addItem(new ItemStack(item));
    }
    public RecipeGroupBuilder addItem(ItemStack stack){
        return addItem(stack, false);
    }
    public RecipeGroupBuilder addItem(ItemStack stack, boolean isInclusive){
        if (isDebug){
            LegacyUI.LOGGER.info(stack.toString());
        }

        if (isInclusive){
            inclusiveItemList.add(stack);
        } else {
            exclusiveItemList.add(stack);
        }

        return this;
    }
    public RecipeGroupBuilder addClass(Class clazz){
        return addClass(clazz, false);
    }
    public RecipeGroupBuilder addClass(Class clazz, boolean isInclusive){
        if (isDebug){
            LegacyUI.LOGGER.info(clazz.getName());
        }

        if (isInclusive){
            inclusiveClassList.add(clazz);
        } else {
            exclusiveClassList.add(clazz);
        }

        return this;
    }
    public RecipeGroupBuilder isDebug(){
        isDebug = true;
        return this;
    }
    public RecipeGroupBuilder printCurrentConfig(){
        LegacyUI.LOGGER.info("isDebug:" + isDebug);
        for (Class<?> clazz : inclusiveClassList){
            LegacyUI.LOGGER.info("inclusiveClass:"+clazz.getName());
        }
        for (ItemStack stack : inclusiveItemList){
            LegacyUI.LOGGER.info("inclusiveItemStack:"+stack);
        }
        for (String keyword : inclusiveKeywordList){
            LegacyUI.LOGGER.info("inclusiveKeyword:"+keyword);
        }
        for (Class<?> clazz : exclusiveClassList){
            LegacyUI.LOGGER.info("exclusiveClass:"+clazz.getName());
        }
        for (ItemStack stack : exclusiveItemList){
            LegacyUI.LOGGER.info("exclusiveItemStack:"+stack);
        }
        for (String keyword : exclusiveKeywordList){
            LegacyUI.LOGGER.info("exclusiveKeyword:"+keyword);
        }
        return this;
    }


    public RecipeGroup build(){
        List<RecipeEntryCrafting<?,?>> unused_copy = new ArrayList<>(unusedRecipes);
        List<RecipeEntryCrafting<?,?>> recipeGroupRecipes = new ArrayList<>();
        int removeOffset = 0;
        for (int i = 0; i < unused_copy.size(); i++) { // Add exclusive Recipes
            RecipeEntryCrafting<?,?> currentRecipe = unused_copy.get(i);
            if (currentRecipe instanceof RecipeEntryCraftingShaped || currentRecipe instanceof RecipeEntryCraftingShapeless){
                ItemStack recipeItem = (ItemStack) currentRecipe.getOutput();
                if (UtilSorting.stackInItemList(excludeItemList,recipeItem)){
                    continue;
                }
                if (UtilSorting.stackInKeywordList(excludeKeywordList,recipeItem)){
                    continue;
                }
                if (UtilSorting.stackInClassList(excludeClassList,recipeItem)){
                    continue;
                }
                if (UtilSorting.stackInClassList(exclusiveClassList,recipeItem) || UtilSorting.stackInItemList(exclusiveItemList, recipeItem) || UtilSorting.stackInKeywordList(exclusiveKeywordList, recipeItem)) {
                    recipeGroupRecipes.add(currentRecipe);
                    unusedRecipes.remove(i - removeOffset);
                    removeOffset++;
                    continue;
                }
            }
        }
        for (RecipeEntryCrafting<?,?> currentRecipe : allRecipes) { // Add inclusive recipes
            if (currentRecipe instanceof RecipeEntryCraftingShaped || currentRecipe instanceof RecipeEntryCraftingShapeless) {
                ItemStack recipeItem = (ItemStack) currentRecipe.getOutput();
                if (UtilSorting.stackInItemList(excludeItemList, recipeItem)) {
                    continue;
                }
                if (UtilSorting.stackInKeywordList(excludeKeywordList, recipeItem)) {
                    continue;
                }
                if (UtilSorting.stackInClassList(excludeClassList, recipeItem)) {
                    continue;
                }
                if (UtilSorting.recipeInRecipeList(recipeGroupRecipes, currentRecipe)) { // Stack already in list
                    continue;
                }
                if (UtilSorting.stackInClassList(inclusiveClassList, recipeItem) || UtilSorting.stackInItemList(inclusiveItemList, recipeItem) || UtilSorting.stackInKeywordList(inclusiveKeywordList, recipeItem)) {
                    recipeGroupRecipes.add(currentRecipe);
                    continue;
                }
            }
        }
        // Dye-recolour recipes ("wool + dye -> coloured wool", "lamp + dye -> coloured lamp", etc.) aren't
        // Shaped/Shapeless recipes at all, so the loops above never see them. For every one that matches this
        // group's filters (checked against its base/white output item), synthesize a normal, real
        // RecipeEntryCraftingShapeless for each dye colour so the rest of our sorting/rendering/crafting code
        // can treat it exactly like any other recipe without needing any special-case handling elsewhere.
        for (RecipeEntryCrafting<?,?> currentRecipe : allRecipes) {
            if (currentRecipe instanceof RecipeEntryDyeing) {
                final RecipeEntryDyeing dyeing = (RecipeEntryDyeing) currentRecipe;
                final ItemStack representative = dyeing.output;
                if (representative == null) {
                    continue;
                }
                if (UtilSorting.stackInItemList(excludeItemList, representative)) {
                    continue;
                }
                if (UtilSorting.stackInKeywordList(excludeKeywordList, representative)) {
                    continue;
                }
                if (UtilSorting.stackInClassList(excludeClassList, representative)) {
                    continue;
                }
                final boolean matchesFilter = UtilSorting.stackInClassList(exclusiveClassList, representative)
                        || UtilSorting.stackInItemList(exclusiveItemList, representative)
                        || UtilSorting.stackInKeywordList(exclusiveKeywordList, representative)
                        || UtilSorting.stackInClassList(inclusiveClassList, representative)
                        || UtilSorting.stackInItemList(inclusiveItemList, representative)
                        || UtilSorting.stackInKeywordList(inclusiveKeywordList, representative);
                if (!matchesFilter) {
                    continue;
                }
                for (final DyeColor color : DyeColor.values()) {
                    final int outputMeta = dyeing.getOutputMetaFromInputMeta(color.itemMeta);
                    if (outputMeta == representative.getMetadata()) {
                        continue; // Skip whichever colour just reproduces the already-existing base/white recipe.
                    }
                    final ItemStack syntheticOutput = new ItemStack(representative.getItem(), representative.stackSize, outputMeta);
                    if (UtilSorting.stackInItemList(excludeItemList, syntheticOutput)) {
                        continue;
                    }
                    final RecipeSymbol dyeSymbol = new RecipeSymbol(new ItemStack(Items.DYE, 1, color.itemMeta));
                    final List<RecipeSymbol> syntheticInput = new ArrayList<>();
                    syntheticInput.add(dyeing.inputSymbol);
                    syntheticInput.add(dyeSymbol);
                    final RecipeEntryCraftingShapeless syntheticRecipe = new RecipeEntryCraftingShapeless(syntheticInput, syntheticOutput);

                    // The recipe-guidebook slot lookup (used elsewhere in the mod to read a recipe's
                    // ingredient list) is keyed by recipe object and only gets populated for recipes
                    // that went through the normal registration/guidebook-building pass. Since this
                    // synthetic recipe never does, we register a matching entry ourselves so that
                    // lookup doesn't come back empty (and null-crash) for it.
                    final List<SlotGuidebook> guidebookSlots = new ArrayList<>();
                    guidebookSlots.add(new SlotGuidebook(0, 0, 0, dyeing.inputSymbol, false, syntheticRecipe));
                    guidebookSlots.add(new SlotGuidebook(1, 0, 0, dyeSymbol, false, syntheticRecipe));
                    guidebookSlots.add(new SlotGuidebook(2, 0, 0, new RecipeSymbol(syntheticOutput), false, syntheticRecipe).setAsOutput());
                    InventoryHelper.recipeMap.put(syntheticRecipe, guidebookSlots);

                    recipeGroupRecipes.add(syntheticRecipe);
                }
            }
        }
        if (isDebug){
            LegacyUI.LOGGER.info("Group");
            UtilSorting.printRecipeList(recipeGroupRecipes);
        }
        UtilSorting.reorderRecipesByExplicitPriority(recipeGroupRecipes, this.exclusiveItemList);
        UtilSorting.sortRecipesByMaterialTierInPlace(recipeGroupRecipes);
        return new RecipeGroup(recipeGroupRecipes);
    }


}
