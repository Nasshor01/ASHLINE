# ASHLINE — kompletní seznam módů

Loader: **Forge 1.20.1**  
Pravidlo: jeden systém na problém. Kde jsou dva kandidáti, je vybraný **jeden**. Druhý je ZÁKAZ.

Slug = CurseForge project slug (URL `curseforge.com/minecraft/mc-mods/SLUG`).  
Před exportem ověř latest *Forge 1.20.1* file. Některé addony mají 1.21 dřív než 1.20 — pokud 1.20.1 JAR není, vyhoď addon, nerozbíjej pack.

---

## A. Loader a knihovny (povinné)

| Mód | Slug | Proč |
|---|---|---|
| Minecraft Forge | (installer) | 47.3.0+ |
| Architectury API | architectury-api | FTB, řada addonů |
| Cloth Config | cloth-config | config GUI |
| Curios API | curios | opasky, ammo box |
| GeckoLib | geckolib | entity animace |
| AzureLib | azurelib | některé entity/zbraně |
| SmartBrainLib | smartbrainlib | AI |
| Resourceful Lib | resourceful-lib | FTB ekosystém |
| Resourceful Config | resourceful-config | dtto |
| CreativeCore | creativecore | visual/ambient |
| Kotlin for Forge | kotlin-for-forge | IV, řada módů |
| Balm | balm | Waystones ZÁKAZ, ale Balm tahají jiní |
| Bookshelf | bookshelf | knihovna |
| Collective | collective | některé utility |
| Cupboard | cupboard | strukturové/perf závislosti |
| Moonlight Lib | moonlight-lib | Farmer's Delight addony |
| Puzzles Lib | puzzles-lib | |
| SuperMartijn642 Config / Core | supermartijn642s-config-lib, supermartijn642s-core-lib | |
| Mixin / MixinSquared | mixinextras (často bundled) | |
| Flywheel | flywheel | Create rendering |
| Forge Config API Port | forge-config-api-port | některé porty |
| PlayerAnimator | playeranimator | |
| Iceberg | iceberg | |
| Prism | prism-lib | |
| OctoLib | octolib | |
| Fusion | fusion-connected-textures | |
| Entity Model Features | entity-model-features | vizuál infected |
| Entity Texture Features | entity-texture-features | vizuál infected |

---

## B. Performance (client + server)

| Mód | Slug | Poznámka |
|---|---|---|
| Embeddium | embeddium | renderer; NE OptiFine, NE Rubidium |
| Embeddium++ / Chloride | embeddium-plus NEBO chloride | jeden |
| Oculus | oculus | shadery, volitelné |
| ModernFix | modernfix | povinné |
| FerriteCore | ferrite-core | povinné |
| Entity Culling | entityculling | |
| ImmediatelyFast | immediatelyfast | |
| BadOptimizations | badoptimizations | |
| Canary | canary | pozor na konflikt se Starlight — testovat |
| Starlight | starlight | pokud Canary konflikt, nechat Starlight |
| Saturn | saturn | RAM |
| Noisium | noisium | chunk gen |
| FastFurnace / FastWorkbench | fastfurnace, fastworkbench | |
| Clumps | clumps | XP |
| Krypton Reforged / Alternate Current | podle kompat | test |
| Smooth Boot (Reloaded) | smooth-boot-reloaded | |
| ServerCore | servercore | server |
| Dynamic FPS | dynamic-fps | client |
| Better FPS - Render Distance | rad volitelně | |
| Distant Horizons | distant-horizons | jen pokud RAM ≥ 12 GB |

**ZÁKAZ:** OptiFine, Rubidium, Sodium (Forge), Create + Flywheel starý konfliktní build, dva lighting engine najednou.

---

## C. Create strom (jeden elektrický addon)

| Mód | Slug |
|---|---|
| Create | create |
| Create Crafts & Additions | createaddition |
| Create Deco | createdeco |
| Create Enchantment Industry | create-enchantment-industry |
| Create Slice & Dice | slice-and-dice |
| Create Steam 'n' Rails | create-steam-n-rails |
| Create Big Cannons | create-big-cannons |
| Ritchie's Projectile Library | ritchies-projectile-lib |
| Create: Copycats+ | copycats |
| Create: Numismatics | create-numismatics | měna osady |
| Create: Design n' Decor / Interiors | dle chuti, max 2 dekor addony |

**Elektřina — VYBER JEDNO:**
- Create New Age (`create-new-age`)  
**NEBO**
- Create: The Factory Must Grow (`create-tfmg`)

**ZÁKAZ:** New Age + TFMG současně.  
**ZÁKAZ:** Mekanism, Thermal, IE jako druhý tech strom. Create je jediný.

Munice:
- Create: Immersive TaCZ Integration (`create-immersive-tacz-integration`) — jen pokud existuje **Forge 1.20.1** file
- Create Armorer gun pack (resource do `tacz/`)
- TaCZ JS (`tacz-js`)

---

## D. Zbraně

| Mód | Slug | Role |
|---|---|---|
| Timeless and Classics Zero | timeliness-and-classics-zero / tacz | **JEDINÝ** gun mód |
| TaCZ: Sound Attracts Zombies | tacz-sound-attracts-zombies | výstřel → sluch |
| TaCZ Durability | tacz-durability | opotřebení |
| Point Blank | ZÁKAZ vedle TaCZ | |
| MrCrayfish Gun Mod | ZÁKAZ | |
| Pipe Guns | pipe-guns | early scrap zbraně, nechat pokud nekoliduje s TaCZ |

Gunpacky (do složky `tacz/`, ne jako extra gun engine):
- Create Armorer
- 1–2 realistic worn packs (ne 15 packů)

---

## E. Infected / AI / infection

| Mód | Slug | Role |
|---|---|---|
| Zombie Awareness | zombie-awareness | sluch + investigation; **VYPNOUT blood scent v configu** |
| Attract to Sound | attract-to-sound | jen pokud Awareness nestačí na TaCZ — **NE oba naplno**, testovat |
| Enhanced AI | enhanced-ai | breaking dveří jen u Breaker typu |
| CoroUtil | coroutil | dep Awareness |
| Contagion NEBO Zombies & Infections | contagion / zombies-infections | **JEDEN** infection mód |
| Relentless Undead | relentless-undead | lezení, jen pokud nekazí FOV pravidlo |
| Mutant variants / Survival Instinct | survival-instinct | vizuály a typy; konfigurovat spawn |
| More Zombie Variants / Custom zombie resource+entity mód | dle dostupnosti 1.20.1 | nahradit vanilla skin |
| Pehkui | pehkui | crawler scale pokud potřeba |
| SpawnCapControl / In Control! | in-control | vypnout indoor spawn, cap ulic |
| Bad Mobs | badmobs | blacklist vanilla zvířat/creeperů dle GDD |
| DoesPotRingStart | — | ne |

**ZÁKAZ:** Epic Siege naplno (ničí base nespravedlivě).  
**ZÁKAZ:** vanilla zombie jako jediný vizuál. Resource pack + variant módy povinné.  
**ZÁKAZ:** Hordes módu, který spawnuje do interiéru.

Doporučený vizuál stack:
- Fresh Animations Zombies (resource)
- ETF + EMF
- vlastní textury v `resourcepacks/ashline-infected/`

---

## F. Svět, města, struktury

| Mód | Slug | Role |
|---|---|---|
| The Lost Cities | lost-cities / the-lost-cities | urban worldgen |
| TerraBlender | terrablender | |
| Terralith | terrablender+terralith | krajina mimo města; **otestovat** s Lost Cities |
| Serene Seasons | serene-seasons | roční období |
| Continents | continents | větší pevniny |
| Structure Gel API | structure-gel-api | vlastní struktury |
| YUNG's API | yungs-api | |
| Towns and Towers | towns-and-towers | střídmě |
| When Dungeons Arise | when-dungeons-arise | **většinu struktur vypnout**, nechat industrial/city-like |
| Additional Structures | additional-structures | filtrovat |
| Repurposed Structures | repurposed-structures | vypnout dungeony/nether |
| Formations Overworld | formations | |
| William Wythers' Overhauled Overworld | overhauled-overworld | alternativa k Terralith, NE obojí |
| Wastelands of Baedoor / similar radiation biome | wastelands-of-baedoor | wasteland pás |
| Berezka API TaCZ addon Lost City | berezka-api-tacz-addon | loot TaCZ do Lost Cities chestů |
| Lootr | lootr | MP chesty; v SP taky OK |
| Structure Essentials | structure-essentials | |

**ZÁKAZ:** Quark massive worldgen + Terralith + Wythers najednou.  
**ZÁKAZ:** The Aether / Twilight jako progres.

Vlastní struktury (datapack ASHLINE, Structure Gel):
1. `hospital_wing`  
2. `power_plant`  
3. `military_checkpoint`  
4. `military_base`  
5. `suburb_school`  
6. `gas_station`  
7. `warehouse_depot`  
8. `faction_militia_outpost`  
9. `faction_cut_bazaar`  
10. `faction_ward_clinic`  
11. `faction_parish_workshop`  
12. `faction_holds_farm`  
13. `choir_nest`  
14. `radio_tower`  
15. `airfield_strip`  
16. `subway_entrance`  
17. `apartment_block_sealed` (schematic forward base)

---

## G. Survival potřeby

| Mód | Slug |
|---|---|
| Tough As Nails | tough-as-nails |
| Thirst Was Taken | thirst-was-taken | **NE spolu s TAN thirst pokud double dip** — vyber TAN NEBO Thirst |
| TAN + Serene Seasons compat | tan-seasons / similar |
| Farmer's Delight | farmers-delight |
| Farmer's Respite | farmers-respite |
| Cultural Delights / Large Meals | max 2 delight addony |
| Spice of Life: Carrot Edition NEBO Onion | spice-of-life-carrot-edition | rotace jídla |
| Food decay: Tick-based via KubeJS **nebo** Food Spoilage mód pokud 1.20.1 Forge existuje | |
| Comforts | comforts | sleeping bag, ne bed exploit |
| Cold Sweat | cold-sweat | alternativa TAN teploty — **NE TAN + Cold Sweat** |
| AppleSkin | appleskin |
| Nutrition / Diet | diet | volitelné, může být moc |

Rozhodnutí packu: **Tough As Nails** (žízeň + teplota) + KubeJS spoil + Farmer's Delight. Nic dalšího na potřeby.

---

## H. Osada, NPC, frakce

| Mód | Slug |
|---|---|
| MineColonies | minecolonies |
| Structurize | structurize |
| BlockUI | blockui |
| Multi-Piston | multi-piston | dep MC |
| Domum Ornamentum | domum-ornamentum |
| Stylecolonies | stylecolonies | volitelné |
| Recruits (Villager Recruits) | recruits |
| Recruits addon | recruits-extras | volitelné |
| Reputation (The_Computerizer) | reputation | 1.20.1 pokud file existuje |
| Guard Villagers | guard-villagers | jen pokud Recruits nestačí na statickou hlídku — default VYPNUTO |
| TACZ: Npcs | tacz-npcs | ozbrojení NPC frakcí |
| Project TaCZ NPCs | ověřit aktuální slug | alternativně |

**ZÁKAZ:** Millénaire.  
MineColonies workers ≠ Recruits soldiers. KubeJS/quests to musí oddělit.

---

## I. Vozidla

| Mód | Slug |
|---|---|
| Immersive Vehicles (MTS/IV) | immersive-vehicles / movement-and-transport-simulator |
| Immersive Vehicles Official Content Pack | transport-simulator-official-vehicle-set |
| ZD Military Vehicles Pack | zd-military-vehicles-renewed |
| Immersive Aircraft | immersive-aircraft | **JEN pokud IV heli/letadla vadí** — default NE |
| Simple Planes | ZÁKAZ vedle IV |
| Valkyrien Skies | valkyrien-skies | V2 late, default **vypnuto v packu**, addon složka |
| [ST]Drive | st-drive | alternativní auta — NE s IV najednou |

Rozhodnutí: **IV + OCP + 1 military pack**. Tečka.

---

## J. Questy, skriptování, pack glue

| Mód | Slug |
|---|---|
| KubeJS | kubejs |
| Rhino | rhino |
| ProbeJS | probejs | dev only, do packu ne |
| Lychee | lychee | volitelné recepty |
| FTB Library | ftb-library-forge |
| FTB Teams | ftb-teams-forge |
| FTB Quests | ftb-quests-forge |
| FTB XMod Compat | ftb-xmod-compat |
| Item Filters | item-filters |
| Quest Infomation / FTB Quests Enhancements | dle chuti |
| PackMenu | packmenu | custom menu |
| Default Options | default-options | |
| Configured | configured | |
| Catalogue | catalogue | |

---

## K. QoL ( držené nakrátko )

| Mód | Slug |
|---|---|
| JEI | jei |
| Jade | jade |
| Jade Addons | jadeaddons |
| Xaero's Minimap | xaeros-minimap | **fair map, bez cave na default** |
| Xaero's World Map | xaeros-world-map |
| XaeroPlus | xaeroplus | volitelné |
| Nature's Compass | natures-compass | vypnout pokud kazí exploraci |
| Explorer's Compass | explorers-compass | jen na struktury ASHLINE, ne vanilla |
| Carry On | carry-on |
| Mouse Tweaks | mouse-tweaks |
| Controlling | controlling |
| Searchables | searchables |
| Inventory Sorter | inventory-sorter |
| Sophisticated Backpacks | sophisticated-backpacks |
| Sophisticated Storage | sophisticated-storage |
| Sophisticated Core | sophisticated-core |
| Traveler's Backpack | ZÁKAZ vedle Sophisticated |
| Storage Drawers | storage-drawers | Create contraption care |
| Tom's Simple Storage | toms-storage | volitelné |
| Trash Cans | trash-cans |
| Visual Workbench | visual-workbench |
| Fast Leaf Decay | fast-leaf-decay |
| Falling Leaves | falling-leaves | vizuál |
| AmbientSounds | ambientsounds |
| Sound Physics Remastered | sound-physics-remastered |
| Presence Footsteps | presence-footsteps |
| Enhanced Visuals | enhanced-visuals | krev/rána, ne overkill
| Not Enough Animations | not-enough-animations |
| Eating Animation | eating-animation |
| Skin Layers 3D | skinlayers3d |
| Shoulder Surfing Reloaded | shoulder-surfing-reloaded | 3rd person střelba TaCZ |
| Combat Roll | combat-roll | volitelné, může kazit stealth |
| Better Combat | ZÁKAZ s TaCZ (conflict) |
| Corpse | corpse | loot po smrti |
| Gravestone | NE s Corpse |
| Cosmetic Armor | cosmetic-armor-reworked |

**ZÁKAZ:** Waystones, Puffish Skills mega RPG, Iron Furnaces, Mystical Agriculture, Mekanism, Applied Energistics (v1 pack — AE2 zabije Create fantasy).

---

## L. Dekor / building (město a base)

| Mód | Slug |
|---|---|
| Macaw's Windows / Doors / Bridges / Trapdoors / Lights | macaw-* (max 6 Macaw) |
| Create Deco | already |
| Another Furniture | another-furniture |
| Handcrafted | handcrafted |
| Security Craft | securitycraft | mříže, kamery late |
| Framed Blocks | framedblocks |
| Rechiseled + Rechiseled: Create | rechiseled |
| Chipped | chipped |
| The Last Day (deco apocalypse) | ověřit slug the-last-day | pokud 1.20.1 |
| Extra Maps / Paper maps | |

---

## M. Resource packy a shadery (overrides)

- ASHLINE Infected textures (vlastní)
- Fresh Animations + zombie addon
- Continuity / Fusion connected textures
- Stay True NEBO default + city ruin pack
- Shader: Complementary Reimagined NEBO Insanity/Hysteria horror — jeden default off

---

## N. Vlastní projekty (AI)

1. `ashline-senses` — FOV, sound lure, cleared buildings  
2. `ashline-factions` — standing, kontrakty, vzpoura (pokud Reputation datapack nestačí)  
3. `ashline-world` — processors, wraky, rádio, palivo hook  

Bez těchto tří pack **není** ASHLINE, je to hromada módů. Senses je povinný. Factions může začít jako Reputation datapack. World může začít jako KubeJS + structures.

---

## O. Tvrdý strop

Cíl: **90–130 módů**, ne 250.  
Každý nový mód musí odpovědět: jakou díru z GDD zalepuje, kterou nic ze seznamu nezalepuje.
