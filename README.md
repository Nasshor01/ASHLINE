# ASHLINE
## Forge 1.20.1 — zombie apokalypsa, Create ekonomika, frakce, města k vyčištění

**Minecraft:** 1.20.1  
**Loader:** Forge 47.3.0+ (doporučeno 47.4.x)  
**Java:** 17  
**RAM:** 8 GB alokace minimum, 10–12 GB ideál  

Tento adresář je **design + implementační bible + šablona packu**.  
Není to hotový nainstalovaný CurseForge zip se všemi JARy (ty se stahují z CurseForge podle `manifest.json` / Packwiz).  
Je to přesný plán, podle kterého Cursor a ty pack složíte tak, aby šel zapnout a hrát.

### Soubory
| Soubor | Účel |
|---|---|
| `docs/00_GDD.md` | herní design, pravidla světa, frakce, zombie, vozidla |
| `docs/01_MODLIST.md` | kompletní seznam módů + slugs + role + ZÁKAZY |
| `docs/02_CURSOR_BIBLE.md` | přesné úkoly pro Cursor, pořadí, akceptační testy |
| `docs/03_RECIPES_MATERIALS.md` | materiály, Create linky, palivo, munice |
| `docs/04_QUESTS.md` | kompletní FTB Quests strom |
| `docs/05_NPC_FACTIONS.md` | NPC, reputace, obchody, vzpoura |
| `docs/06_STRUCTURES_LOOT.md` | budovy, loot, clear/seal |
| `docs/07_SOUNDS_SENSES.md` | tabulka hluku, FOV, distrakce |
| `docs/08_STATUS.md` | co je hotové / co doma |
| `manifest.json` / `pack.toml` | CurseForge + packwiz kostra (`files: []`) |
| `overrides/` | **kanonický** pack obsah (config, kubejs, datapacks, resourcepacks) |
| `kubejs/` / `datapacks/` | zrcadla z `overrides/` |
| `src/ashline-senses/` | vlastní mód (FOV, lure, building clear, standing) |
| `cursor/PROMPTS.md` | copy-paste prompty po ticích |

### Pravda na začátek
Cursor nenainstaluje 120 JAR souborů ani neověří, že Lost Cities + MineColonies + IV naběhnou napoprvé.  
Cursor napíše KubeJS, datapacky, quest JSON, configy a vlastní malý mód podle bible.  
Ty (nebo Packwiz) stáhneš módy ze seznamu. První boot = smoke test ze `02_CURSOR_BIBLE.md`.
