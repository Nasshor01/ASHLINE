# ASHLINE — stav implementace (bez CurseForge)

## Hotovo
- **T5** `src/ashline-senses` — FOV, pebble lure, BuildingTracker, **Gradle wrapper**, `AshlineSenses.emitSound`, **build OK** (`build/libs/ashline-senses-1.0.0.jar`)
- **T6** KubeJS v `overrides/kubejs` (mirror `kubejs/`)
- **T7** FTB Quests kapitoly 0–10; standing rewardy napojené na commandy
- **T8** standing v `ashline-senses` + datapack frakce `overrides/datapacks/ashline/data/ashline/factions/` (mirror `datapacks/`)
- **T2** In Control kostra `overrides/config/incontrol/spawn.json`
- Bible config placeholdery: zombieawareness, TAN, Create, TaCZ, MineColonies, Lost Cities, IV + `defaultconfigs/`
- Resource pack stub `overrides/resourcepacks/ashline-infected/`
- `pack.toml` + `manifest.json` `0.2.0-wip` (`files: []`)

## Standing
```
ashline standing add @s militia 80
ashline standing get @s cut
ashline standing set @s choir -400
```
Frakce: militia, cut, ward, parish, holds, choir. Clamp **−1000…1000**. Overworld SavedData `ashline_standing`.  
Questy s `elevate_permisssions` command rewardy fungují; manuální add/set vyžaduje permission 2.

## Až budeš doma
1. CurseForge custom pack 1.20.1 Forge 47.4.x, Java 17, 8–12 GB RAM
2. T0: Embeddium, ModernFix, FerriteCore, JEI, Jade, KubeJS+Rhino
3. Zkopíruj `overrides/` do instance (config, kubejs, datapacks, resourcepacks)
4. JAR: buď `src/ashline-senses/build/libs/ashline-senses-1.0.0.jar`, nebo `gradlew.bat build`
5. Ověř `/ashline standing get @s militia` a quest reward
6. Volitelně `packwiz curseforge add` podle `docs/01_MODLIST.md`

## Ještě ne
- CurseForge ProjectID / `files[]` v manifestu
- **T9** MineColonies cap + Recruits hire cost
- **T10** IV wraky + engine → emitSound
- **T11** vlastní struktury / loot processors
- Art: infected skins v resource packu
- Lost Cities profil JSON až bude JAR
- Plný `ashline-factions` (NPC/kontrakty/vzpoura) — standing už běží v senses
