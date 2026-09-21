package useless.legacyui.sorting;

import net.minecraft.core.block.*;
import net.minecraft.core.block.piston.BlockLogicPistonBase;
import net.minecraft.core.item.*;
import net.minecraft.core.item.tool.*;
import net.minecraft.core.util.helper.DyeColor;
import useless.legacyui.LegacyUI;
import useless.legacyui.sorting.item.ItemCategory;
import useless.legacyui.sorting.item.ItemCategoryBuilder;
import useless.legacyui.sorting.recipe.RecipeCategory;
import useless.legacyui.sorting.recipe.RecipeCategoryBuilder;
import useless.legacyui.sorting.recipe.RecipeGroupBuilder;

import java.util.ArrayList;
import java.util.List;

public class LegacyCategoryManager {
    private static final List<RecipeCategory> recipeCategories = new ArrayList<>();
    private static final List<ItemCategory> creativeCategories = new ArrayList<>();
    public static List<ItemCategoryBuilder> creativeCategoriesBuilders = new ArrayList<>();
    public static List<RecipeCategoryBuilder> recipeCategoryBuilders = new ArrayList<>();
    public static String MOD_ID = LegacyUI.MOD_ID;
    public static List<RecipeCategory> getRecipeCategories(){
        return recipeCategories;
    }
    public static List<ItemCategory> getCreativeCategories(){
        return creativeCategories;
    }
    public static void build(){
        for (final ItemCategoryBuilder builder: creativeCategoriesBuilders) {
            creativeCategories.add(builder.build());
        }
        for (final RecipeCategoryBuilder builder: recipeCategoryBuilders) {
            recipeCategories.add(builder.build());
        }
    }
    public static class creative{
        public static ItemCategoryBuilder equipment = new ItemCategoryBuilder(MOD_ID)
            .addClass(ItemTool.class)
            .addClass(ItemToolSword.class)
            .addClass(ItemFishingRod.class)
            .addClass(ItemBow.class)
            .addItem(Items.AMMO_ARROW)
            .addItem(Items.AMMO_ARROW_GOLD)
            .addItem(Items.AMMO_ARROW_PURPLE)
            .addClass(ItemHandCannonLoaded.class)
            .addClass(ItemHandCannonUnloaded.class)
            .addItem(Items.AMMO_CHARGE_EXPLOSIVE)
            .addClass(ItemFireStriker.class)
            .addClass(ItemBucket.class)
            .addClass(ItemLabel.class)
            .addClass(ItemToolShears.class)
            .addItem(Items.TOOL_COMPASS)
            .addItem(Items.TOOL_CLOCK)
            .addItem(Items.TOOL_CALENDAR)
            .addItem(Items.MAP)
            .addClass(ItemArmor.class)
            .addItem(Items.ARMOR_BOOTS_ICESKATES)
            .addClass(ItemQuiver.class)
            .addClass(ItemQuiverEndless.class)
            .addClass(ItemPaintBrush.class)
            .addClass(ItemWandSpawner.class)
            .addItem(Items.JAR)
            .setIcon("legacyui:gui/icon/tools")
            .setTranslationKey("equipment");
        public static ItemCategoryBuilder food = new ItemCategoryBuilder(MOD_ID)
            .addClass(ItemFood.class)
            .addItem(Items.FOOD_CAKE)
            .addItem(Items.FOOD_PUMPKIN_PIE)
            .setIcon("legacyui:gui/icon/health")
            .setTranslationKey("food");
        public static ItemCategoryBuilder redstoneTransit = new ItemCategoryBuilder(MOD_ID)
                .addClass(BlockLogicRail.class)
                .addClass(BlockLogicRedstone.class,true)
                .addItem(Items.DUST_REDSTONE)
                .addClass(BlockLogicTorchRedstone.class)
                .addClass(BlockLogicTNT.class)
                .addClass(BlockLogicDispenser.class)
                .addClass(BlockLogicNote.class)
                .addClass(BlockLogicSpikes.class)
                .addClass(BlockLogicMesh.class)
                .addItem(Items.REPEATER)
                .addClass(ItemMinecart.class)
                .addClass(ItemBoat.class)
                .addClass(ItemSaddle.class)
                .addClass(BlockLogicPistonBase.class)
                .addClass(BlockLogicMotionSensor.class)
                .addClass(BlockLogicDoor.class, true)
                .addClass(ItemDoor.class, true)
                .addClass(BlockLogicTrapDoor.class, true)
                .addClass(BlockLogicLamp.class)
                .addClass(BlockLogicLever.class)
                .addClass(BlockLogicButton.class)
                .addClass(BlockLogicPressurePlate.class)
                .addClass(BlockLogicSpikes.class)
                .addItem(Items.BASKET)
                .setIcon("legacyui:gui/icon/redstonerail")
                .setTranslationKey("redstone");
        public static ItemCategoryBuilder natural = new ItemCategoryBuilder(MOD_ID)
                .addClass(BlockLogicLeavesBase.class)
                .addClass(BlockLogicPathDirt.class)
                .addClass(BlockLogicLog.class)
                .addClass(BlockLogicMud.class)
                .addClass(BlockLogicFlower.class)
                .addClass(BlockLogicStone.class)
                .addClass(BlockLogicLayerBase.class)
                .addClass(BlockLogicMoss.class)
                .addClass(BlockLogicAlgae.class)
                .addClass(BlockLogicSoulSand.class)
                .addItem(Blocks.COBBLE_NETHERRACK)
                .addItem(Blocks.PUMPKIN)
                .addClass(BlockLogicIce.class)
                .addItem(Blocks.OBSIDIAN)
                .addClass(BlockLogicGlowStone.class)
                .addClass(BlockLogicBedrock.class)
                .addClass(BlockLogicClay.class)
                .addClass(BlockLogicSnow.class)
                .addClass(BlockLogicSponge.class)
                .addClass(BlockLogicCactus.class)
                .addClass(BlockLogicCobweb.class)
                .excludeItem(Blocks.SPIKES.asItem())
                .addItem(Blocks.MARBLE)
                .addItem(Blocks.SLATE)
                .addItem(Blocks.SANDSTONE)
                .addClass(BlockLogicGrass.class)
                .addItem(Blocks.DIRT)
                .addItem(Blocks.DIRT_SCORCHED)
                .addItem(Blocks.DIRT_SCORCHED_RICH)
                .addClass(BlockLogicFarmland.class)
                .addItem(Blocks.BONESHALE)
                .addKeyword("tile.ore.")
                .setIcon("legacyui:gui/icon/grass")
                .setTranslationKey("natural");
        public static ItemCategoryBuilder otherBlocks = new ItemCategoryBuilder(MOD_ID)
                .excludeItem(Blocks.TORCH_REDSTONE_IDLE.asItem())
                .excludeItem(Blocks.TORCH_REDSTONE_ACTIVE.asItem())
                .excludeItem(Blocks.LEVER_COBBLE_STONE.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_STONE.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_COBBLE_STONE.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_PLANKS_OAK.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_PLANKS_OAK_PAINTED.asItem())
                .addClass(BlockLogicWool.class)
                .addClass(BlockLogicFence.class)
                .addClass(BlockLogicFenceGate.class)
                .addClass(BlockLogicFenceChainlink.class)
                .addClass(BlockLogicTorch.class)
                .addClass(BlockLogicLadder.class)
                .addClass(BlockLogicSlab.class)
                .addClass(BlockLogicStairs.class)
                .addClass(BlockLogicGlass.class)
                .addClass(BlockLogicPumpkin.class)
                .addClass(BlockLogicTrommel.class)
                .addClass(BlockLogicWorkbench.class)
                .addClass(BlockLogicChest.class)
                .addClass(BlockLogicSign.class)
                .addClass(BlockLogicFurnace.class)
                .addClass(BlockLogicJukebox.class)
                .addClass(ItemSign.class)
                .addClass(ItemPainting.class)
                .addClass(ItemFlag.class)
                .addItem(Items.ROPE)
                .addItem(Items.SEAT)
                .addClass(BlockLogicDoor.class, true)
                .addClass(ItemDoor.class, false)
                .addClass(BlockLogicTrapDoor.class, true)
                .addClass(BlockLogicMobSpawner.class)
                .addItem(Blocks.MOBSPAWNER_DEACTIVATED)
                .addItem(Blocks.PERMAICE)
                .addItem(Blocks.PILLAR_MARBLE)
                .addItem(Items.BED)
                .addItem(Items.LANTERN_FIREFLY_RED)
                .addItem(Items.LANTERN_FIREFLY_BLUE)
                .addItem(Items.LANTERN_FIREFLY_GREEN)
                .addItem(Items.LANTERN_FIREFLY_ORANGE)
                .addKeyword("tile.block.")
                .addKeyword(".polished")
                .addKeyword(".cobble.")
                .addKeyword(".planks.")
                .addKeyword("tile.brick.")
                .setIcon("legacyui:gui/icon/bricks")
                .setTranslationKey("construction");
        public static ItemCategoryBuilder misc = new ItemCategoryBuilder(MOD_ID)
                .addClass(Block.class)
                .addClass(Item.class)
                .excludeModdedItems()
                .setIcon("legacyui:gui/icon/painting")
                .setTranslationKey("misc");
        public static ItemCategoryBuilder modded = new ItemCategoryBuilder(MOD_ID)
                .isDebug()
                .setIcon("legacyui:gui/icon/modded")
                .setTranslationKey("modded")
                .isModded();
    }
    public static class recipeBasics {
        public static RecipeGroupBuilder planks = new RecipeGroupBuilder()
                .addItem(Blocks.PLANKS_OAK)
                .addItemsWithMetaRange(Blocks.PLANKS_OAK_PAINTED.asItem(), 0, 16, false);
        public static RecipeGroupBuilder torches = new RecipeGroupBuilder()
                .addClass(BlockLogicTorch.class)
                .addItem(Items.STICK)
                .excludeItem(Blocks.TORCH_REDSTONE_ACTIVE.asItem());
        public static RecipeGroupBuilder utilityBlocks = new RecipeGroupBuilder()
                .addItem(Blocks.WORKBENCH)
                .addItem(Blocks.JUKEBOX)
                .addItem(Blocks.FURNACE_BLAST_IDLE)
                .addItem(Blocks.FURNACE_STONE_IDLE)
                .addItem(Blocks.TROMMEL_IDLE);
        public static RecipeGroupBuilder chest = new RecipeGroupBuilder()
                .addItem(Blocks.CHEST_PLANKS_OAK)
                .addClass(BlockLogicChest.class);
        public static RecipeGroupBuilder bed = new RecipeGroupBuilder()
                .addItem(Items.BED)
                .addItem(Items.SEAT);
        public static RecipeGroupBuilder fences = new RecipeGroupBuilder()
                .addItem(Blocks.FENCE_PLANKS_OAK)
                .addClass(BlockLogicFence.class);
        public static RecipeGroupBuilder fencegates = new RecipeGroupBuilder()
                .addItem(Blocks.FENCE_GATE_PLANKS_OAK)
                .addClass(BlockLogicFenceGate.class);
        public static RecipeGroupBuilder woodStairs = new RecipeGroupBuilder()
                .addItem(Blocks.STAIRS_PLANKS_OAK)
                .addKeyword("stairs.planks");
        public static RecipeGroupBuilder woodSlabs = new RecipeGroupBuilder()
                .addItem(Blocks.SLAB_PLANKS_OAK)
                .addKeyword("slab.planks");
        public static RecipeGroupBuilder ladders = new RecipeGroupBuilder()
                .addItem(Blocks.LADDER_OAK)
                .addItem(Items.ROPE)
                .addClass(BlockLogicLadder.class)
                .addClass(BlockLogicFenceChainlink.class);
        public static RecipeGroupBuilder doors = new RecipeGroupBuilder()
                .addItem(Items.DOOR_OAK)
                .addKeyword("door");
        public static RecipeGroupBuilder books = new RecipeGroupBuilder()
                .addItem(Items.PAPER)
                .addItem(Blocks.BOOKSHELF_PLANKS_OAK)
                .addItem(Items.BOOK);
        public static RecipeGroupBuilder wool = new RecipeGroupBuilder()
                .addClass(BlockLogicWool.class);
        public static RecipeGroupBuilder display = new RecipeGroupBuilder()
                .addItem(Items.SIGN)
                .addClass(ItemSign.class)
                .addClass(ItemPainting.class);
        public static RecipeCategoryBuilder category = new RecipeCategoryBuilder(MOD_ID)
                .setTranslationKey("basics")
                .setIcon("legacyui:gui/icon/planks")
                .addRecipeGroupBuilders(planks, torches, utilityBlocks, chest, bed, fences, fencegates, woodStairs, woodSlabs, ladders, doors, books, wool, display);
    }
    public static class recipeBricks {
        public static RecipeGroupBuilder stoneStairs = new RecipeGroupBuilder()
                .addItem(Blocks.STAIRS_BRICK_STONE)
                .addKeyword(".stairs.");
        public static RecipeGroupBuilder stoneSlabs = new RecipeGroupBuilder()
                .addItem(Blocks.SLAB_BRICK_STONE)
                .addKeyword(".slab.");
        public static RecipeGroupBuilder bricks = new RecipeGroupBuilder()
                .addItem(Blocks.BRICK_STONE)
                .addKeyword(".brick.")
                .excludeKeyword(".stairs.")
                .excludeKeyword(".slab.");
        public static RecipeGroupBuilder polished = new RecipeGroupBuilder()
                .addItem(Blocks.STONE_POLISHED)
                .addKeyword(".polished")
                .addKeyword(".pillar")
                .addKeyword(".carved")
                .excludeKeyword(".stairs.")
                .excludeKeyword(".slab.")
                .excludeItem(Blocks.PUMPKIN.asItem())
                .excludeItem(Blocks.PUMPKIN_CARVED_ACTIVE.asItem())
                .excludeItem(Blocks.PUMPKIN_CARVED_IDLE.asItem());
        public static RecipeGroupBuilder natural = new RecipeGroupBuilder()
                .addItem(Blocks.COBBLE_STONE)
                .addItem(Blocks.SANDSTONE)
                .addItem(Blocks.GRAVEL)
                .addItem(Blocks.BLOCK_SNOW)
                .addItem(Blocks.BLOCK_CLAY)
                .addKeyword(".cobble.")
                .excludeItem(Blocks.DISPENSER_COBBLE_STONE.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_COBBLE_STONE.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_COBBLE_BASALT.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_COBBLE_LIMESTONE.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_COBBLE_GRANITE.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_COBBLE_PERMAFROST.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_COBBLE_NETHERRACK.asItem())
                .excludeItem(Blocks.PRESSURE_PLATE_COBBLE_GLOOMSTONE.asItem())
                .excludeItem(Blocks.LEVER_COBBLE_STONE.asItem())
                .excludeItem(Blocks.ACTIVATOR.asItem())
                .excludeItem(Blocks.SPONGE_WET.asItem())
                .addClass(BlockLogicIce.class)
                .addItem(Blocks.PERMAICE)
                .addClass(BlockLogicPumpkin.class)
                .addClass(BlockLogicSponge.class);
        public static RecipeGroupBuilder layers = new RecipeGroupBuilder()
                .addItem(Blocks.LAYER_SNOW)
                .addClass(BlockLogicLayerBase.class);
        public static RecipeGroupBuilder resourceBlocks = new RecipeGroupBuilder()
                .addItem(Blocks.BLOCK_IRON)
                .addItem(Blocks.BLOCK_GOLD)
                .addItem(Blocks.BLOCK_DIAMOND)
                .addItem(Blocks.BLOCK_LAPIS)
                .addItem(Blocks.BLOCK_STEEL)
                .addItem(Blocks.BLOCK_REDSTONE)
                .addItem(Blocks.BLOCK_QUARTZ)
                .addItem(Blocks.BLOCK_COAL)
                .addItem(Blocks.BLOCK_CHARCOAL)
                .addItem(Blocks.BLOCK_NETHER_COAL)
                .addItem(Blocks.BLOCK_OLIVINE);
        public static RecipeGroupBuilder resourceBlocksUncompacts = new RecipeGroupBuilder()
                .addItem(Items.INGOT_IRON)
                .addItem(Items.INGOT_GOLD)
                .addItem(Items.DIAMOND)
                .addItem(Items.DYE, DyeColor.BLUE.itemMeta, true)
                .addItem(Items.INGOT_STEEL)
                .addItem(Items.INGOT_STEEL_CRUDE)
                .addItem(Items.DUST_REDSTONE)
                .addItem(Items.QUARTZ)
                .addItem(Items.COAL)
                .addItem(Items.COAL.id, 1)
                .addItem(Items.NETHERCOAL)
                .addItem(Items.OLIVINE);
        public static RecipeGroupBuilder statues = new RecipeGroupBuilder()
                .addItem(Items.STATUE_STONE)
                .addClass(ItemStatue.class);
        public static RecipeCategoryBuilder category = new RecipeCategoryBuilder(MOD_ID)
                .setTranslationKey("bricks")
                .setIcon("legacyui:gui/icon/bricks")
                .addRecipeGroupBuilders(bricks, polished, stoneStairs, stoneSlabs, natural, layers, resourceBlocks, resourceBlocksUncompacts, statues);
    }
    public static class recipeTools {
        public static RecipeGroupBuilder pickaxe = new RecipeGroupBuilder()
                .addClass(ItemToolPickaxe.class);
        public static RecipeGroupBuilder shovel = new RecipeGroupBuilder()
                .addClass(ItemToolShovel.class);
        public static RecipeGroupBuilder axe = new RecipeGroupBuilder()
                .addClass(ItemToolAxe.class);
        public static RecipeGroupBuilder hoe = new RecipeGroupBuilder()
                .addClass(ItemToolHoe.class);
        public static RecipeGroupBuilder sword = new RecipeGroupBuilder()
                .addClass(ItemToolSword.class);
        public static RecipeGroupBuilder fishing = new RecipeGroupBuilder()
                .addClass(ItemFishingRod.class);
        public static RecipeGroupBuilder bow = new RecipeGroupBuilder()
                .addItem(Items.TOOL_BOW)
                .addClass(ItemBow.class)
                .addItem(Items.HANDCANNON_UNLOADED)
                .addClass(ItemHandCannonUnloaded.class)
                .addClass(ItemHandCannonLoaded.class)
                .addItem(Items.AMMO_CHARGE_EXPLOSIVE)
                .addItem(Items.AMMO_ARROW_FLAMING)
                .addItem(Items.AMMO_ARROW_GOLD)
                .addItem(Items.AMMO_ARROW)
                .addItem(Items.AMMO_ARROW_PURPLE);
        public static RecipeGroupBuilder handcannon = new RecipeGroupBuilder();
        public static RecipeGroupBuilder miscTools = new RecipeGroupBuilder()
                .addItem(Items.TOOL_FIRESTRIKER_IRON)
                .addClass(ItemFireStriker.class)
                .addClass(ItemBucket.class)
                .addClass(ItemLabel.class)
                .addClass(ItemToolShears.class);
        public static RecipeGroupBuilder toolInfo = new RecipeGroupBuilder()
                .addItem(Items.TOOL_COMPASS)
                .addItem(Items.TOOL_CLOCK)
                .addItem(Items.TOOL_CALENDAR)
                .addItem(Items.MAP);
        public static RecipeGroupBuilder helmet = new RecipeGroupBuilder()
                .addKeyword(".helmet.");
        public static RecipeGroupBuilder chestplate = new RecipeGroupBuilder()
                .addKeyword(".chestplate.")
                .addClass(ItemQuiver.class)
                .addClass(ItemQuiverEndless.class);
        public static RecipeGroupBuilder leggings = new RecipeGroupBuilder()
                .addKeyword(".leggings.");
        public static RecipeGroupBuilder boots = new RecipeGroupBuilder()
                .addKeyword(".boots.");
        public static RecipeGroupBuilder wolfArmor = new RecipeGroupBuilder()
                .addItem(Items.ARMOR_WOLF_LEATHER)
                .addItem(Items.ARMOR_WOLF_IRON)
                .addItem(Items.ARMOR_WOLF_GOLD)
                .addItem(Items.ARMOR_WOLF_DIAMOND)
                .addItem(Items.ARMOR_WOLF_STEEL);
        public static RecipeCategoryBuilder category = new RecipeCategoryBuilder(MOD_ID)
                .setTranslationKey("equipment")
                .setIcon("legacyui:gui/icon/tools")
                .addRecipeGroupBuilders(pickaxe,shovel,axe,hoe,sword,fishing,bow,miscTools,toolInfo,helmet,chestplate,leggings,boots,wolfArmor);
    }
    public static class recipeFood {
        public static RecipeGroupBuilder stew = new RecipeGroupBuilder()
                .addClass(ItemSoup.class)
                .addItem(Items.BOWL);
        public static RecipeGroupBuilder cake = new RecipeGroupBuilder()
                .addItem(Items.FOOD_CAKE)
                .addItem(Items.FOOD_PUMPKIN_PIE);
        public static RecipeGroupBuilder cookies = new RecipeGroupBuilder()
                .addItem(Items.FOOD_COOKIE)
                .addItem(Items.DOUGH);
        public static RecipeGroupBuilder apple = new RecipeGroupBuilder()
                .addItem(Items.FOOD_APPLE)
                .addItem(Items.FOOD_APPLE_GOLD);
        public static RecipeGroupBuilder sugar = new RecipeGroupBuilder()
                .addItem(Items.DUST_SUGAR);
        public static RecipeGroupBuilder foodGeneral = new RecipeGroupBuilder()
                .addClass(ItemFood.class);
        public static RecipeGroupBuilder seeds = new RecipeGroupBuilder()
                .addItem(Items.SEEDS_PUMPKIN);
        public static RecipeCategoryBuilder category = new RecipeCategoryBuilder(MOD_ID)
                .setTranslationKey("food")
                .setIcon("legacyui:gui/icon/health")
                .addRecipeGroupBuilders(stew,cake,cookies,apple,sugar, foodGeneral, seeds);
    }
    public static class recipeRedstone {
        public static RecipeGroupBuilder basicRedstone = new RecipeGroupBuilder()
                .addItem(Blocks.TORCH_REDSTONE_ACTIVE)
                .addItem(Items.REPEATER)
                .addItem(Items.TIMER)
                .addItem(Items.DUST_REDSTONE, true);
        public static RecipeGroupBuilder redstoneBlock = new RecipeGroupBuilder()
                .addClass(BlockLogicRedstone.class, true);
        public static RecipeGroupBuilder buttonLever = new RecipeGroupBuilder()
                .addItem(Blocks.LEVER_COBBLE_STONE)
                .addClass(BlockLogicButton.class)
                .addClass(BlockLogicLever.class);
        public static RecipeGroupBuilder pressureplates = new RecipeGroupBuilder()
                .addItem(Blocks.PRESSURE_PLATE_STONE)
                .addClass(BlockLogicPressurePlate.class);
        public static RecipeGroupBuilder pistons = new RecipeGroupBuilder()
                .addClass(BlockLogicPistonBase.class);
        public static RecipeGroupBuilder lamps = new RecipeGroupBuilder()
                .addClass(BlockLogicLamp.class);
        public static RecipeGroupBuilder noteBlock = new RecipeGroupBuilder()
                .addClass(BlockLogicNote.class);
        public static RecipeGroupBuilder dispensers = new RecipeGroupBuilder()
                .addClass(BlockLogicDispenser.class);
        public static RecipeGroupBuilder traps = new RecipeGroupBuilder()
                .addClass(BlockLogicSpikes.class)
                .addClass(BlockLogicMesh.class)
                .addItem(Items.BASKET);
        public static RecipeGroupBuilder tnt = new RecipeGroupBuilder()
                .addClass(BlockLogicTNT.class);
        public static RecipeGroupBuilder conduit = new RecipeGroupBuilder()
                .addItem(Blocks.CONDUIT)
                .addItem(Blocks.MATCHER)
                .addItem(Blocks.MOTION_SENSOR_IDLE)
                .addItem(Blocks.ACTIVATOR);
        public static RecipeGroupBuilder matcher = new RecipeGroupBuilder();
        public static RecipeGroupBuilder motionSensor = new RecipeGroupBuilder();
        public static RecipeGroupBuilder activator = new RecipeGroupBuilder();

        public static RecipeCategoryBuilder category = new RecipeCategoryBuilder(MOD_ID)
                .setIcon("legacyui:gui/icon/lever")
                .setTranslationKey("redstone")
                .addRecipeGroupBuilders(basicRedstone, redstoneBlock, buttonLever, pressureplates,pistons,lamps,noteBlock,dispensers,traps, tnt, conduit);
    }
    public static class recipeTransit {
        public static RecipeGroupBuilder baseRail = new RecipeGroupBuilder()
                .addItem(Blocks.RAIL);
        public static RecipeGroupBuilder detectRail = new RecipeGroupBuilder()
                .addItem(Blocks.RAIL_DETECTOR);
        public static RecipeGroupBuilder powerRail = new RecipeGroupBuilder()
                .addItem(Blocks.RAIL_POWERED);
        public static RecipeGroupBuilder allRail = new RecipeGroupBuilder()
                .addClass(BlockLogicRail.class);
        public static RecipeGroupBuilder baseCart = new RecipeGroupBuilder()
                .addItem(Items.MINECART);
        public static RecipeGroupBuilder chestCart = new RecipeGroupBuilder()
                .addItem(Items.MINECART_CHEST);
        public static RecipeGroupBuilder furnaceCart = new RecipeGroupBuilder()
                .addItem(Items.MINECART_FURNACE);
        public static RecipeGroupBuilder allCart = new RecipeGroupBuilder()
                .addClass(ItemMinecart.class);
        public static RecipeGroupBuilder boat = new RecipeGroupBuilder()
                .addClass(ItemBoat.class);
        public static RecipeCategoryBuilder category = new RecipeCategoryBuilder(MOD_ID)
                .setIcon("legacyui:gui/icon/rail")
                .setTranslationKey("travel")
                .addRecipeGroupBuilders(baseRail,powerRail,detectRail,allRail,baseCart,chestCart,furnaceCart,allCart,boat);
    }
    public static class recipeMisc{
        public static RecipeGroupBuilder dyes = new RecipeGroupBuilder()
                .addClass(ItemDye.class);
        public static RecipeGroupBuilder allBlocks = new RecipeGroupBuilder()
                .addClass(Block.class)
                .excludeClass(BlockLogicWool.class)
                .excludeClass(ItemSign.class)
                .excludeClass(BlockLogicStairs.class)
                .excludeClass(BlockLogicSlabPainted.class)
                .excludeClass(BlockLogicPressurePlatePainted.class)
                .excludeClass(BlockLogicPlanksPainted.class)
                .excludeClass(BlockLogicLamp.class)
                .excludeClass(BlockLogicFence.class)
                .excludeClass(BlockLogicFenceGate.class)
                .excludeClass(BlockLogicChest.class)
                .excludeClass(BlockLogicButtonPainted.class)
                .excludeKeyword("door");
        public static RecipeGroupBuilder allItems = new RecipeGroupBuilder()
                .addClass(Item.class)
                .excludeClass(BlockLogicWool.class)
                .excludeClass(ItemSign.class)
                .excludeClass(BlockLogicStairs.class)
                .excludeClass(BlockLogicSlabPainted.class)
                .excludeClass(BlockLogicPressurePlatePainted.class)
                .excludeClass(BlockLogicPlanksPainted.class)
                .excludeClass(BlockLogicLamp.class)
                .excludeClass(BlockLogicFence.class)
                .excludeClass(BlockLogicFenceGate.class)
                .excludeClass(BlockLogicChest.class)
                .excludeClass(BlockLogicButtonPainted.class)
                .excludeKeyword("door");
        public static RecipeGroupBuilder all = new RecipeGroupBuilder()
                .addItem(Blocks.STONE)
                .addKeyword(".")
                .excludeClass(BlockLogicWool.class)
                .excludeClass(ItemSign.class)
                .excludeClass(BlockLogicStairs.class)
                .excludeClass(BlockLogicSlabPainted.class)
                .excludeClass(BlockLogicPressurePlatePainted.class)
                .excludeClass(BlockLogicPlanksPainted.class)
                .excludeClass(BlockLogicLamp.class)
                .excludeClass(BlockLogicFence.class)
                .excludeClass(BlockLogicFenceGate.class)
                .excludeClass(BlockLogicChest.class)
                .excludeClass(BlockLogicButtonPainted.class)
                .excludeKeyword("door");
        public static RecipeGroupBuilder flagPaintbrush = new RecipeGroupBuilder()
                .addItem(Items.FLAG)
                .addClass(ItemPaintBrush.class);
        public static RecipeGroupBuilder wetSponge = new RecipeGroupBuilder()
                .addItem(Blocks.SPONGE_WET);
        public static RecipeCategoryBuilder category = new RecipeCategoryBuilder(MOD_ID)
                .setTranslationKey("misc")
                .setIcon("legacyui:gui/icon/painting")
                .addRecipeGroupBuilders(dyes,flagPaintbrush,allBlocks,allItems,all,wetSponge);
    }
}
