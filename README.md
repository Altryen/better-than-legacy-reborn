# Better Than Legacy (BTA 8.0.1 Port)

A GUI overhaul mod for **Better Than Adventure!** that brings back the look, feel, and controller-friendly navigation of the Legacy Console Editions of Minecraft.

## Credits

This mod was originally created by **[UselessBullets](https://modrinth.com/user/UselessBullets)** for BTA 7.3.3. This release is a community port/continuation, updated to work with **BTA 8.0.1**, with the help of AI-assisted development for the porting process. All credit for the original design, concept, and codebase goes to **[UselessBullets](https://modrinth.com/user/UselessBullets)**!

## What's included

- Legacy-style Survival Inventory GUI
- Legacy-style Crafting GUI
- Legacy-style Creative Mode GUI
- Legacy-style Flag/Banner Editor GUI
- Full controller support, designed to feel like the console editions

## What changed in this 8.0.1 port

**Compatibility & Stability**
- Full rebuild against BTA 8.0.1's updated APIs (rendering, textures, containers, inventory, recipes)
- Fixed numerous rendering glitches caused by BTA 8.0.1's move to a modern OpenGL Core Profile (old-style direct OpenGL calls don't work anymore — these have all been replaced)

**Dark Mode & UI Cleanup**
- New optional Dark Mode, toggleable in Options → Better Than Legacy → Legacy GUI Settings, covering every Legacy screen plus the vanilla Furnace, Blast Furnace, Trommel, Activator, Dispenser, and Chest GUIs
- Massively improved general UI layout — fixed overlapping elements and spacing issues so every text and icon is fully readable and nothing gets cut off or covered
- Cleaned up and reorganized item ordering across all crafting categories, so the correct/base version of an item always shows first instead of a random variant
- Wool and Lamp color variants are now browsable and craftable directly from the menu, not just the plain white version

**Language / Localization**
- Added full **German language support** [BTA! German Language Pack](https://modrinth.com/resourcepack/bta-german-language-pack)
- Built a general system so the mod's text can follow more than one language, soon there will be more supportet languages!

**Controller Controls**
- Added left analog stick navigation in the crafting menu (previously D-pad only)
- Fixed the mouse cursor incorrectly showing/being movable while in the crafting table (should only happen in chest-style inventories)
- Swapped X/Y actions to match the console versions (X = take half, Y = quick move)

## Requirements

- [Turnip-Labs BTA Babric instance](https://github.com/Turnip-Labs/bta-fabric-instance-repo) (v8.0.1)
- HalpLibe
- ModMenu (BTA) — optional, for in-game mod settings

---

*This is a clientside-only mod — it works fine in multiplayer without the server needing it installed.*
