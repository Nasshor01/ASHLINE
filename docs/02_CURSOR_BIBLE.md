# ASHLINE — Cursor Bible

Ty jsi implementátor packu **ASHLINE** pro Minecraft **1.20.1 Forge**.  
Nejses game designer. GDD v `docs/` je zákon. Neměň pravidla, jen je kóduj.

## 0. Co NIKDY nedělej

- Neinstaluj Waystones, Mekanism, AE2, Thermal, Point Blank + TaCZ najednou, OptiFine.
- Nedělej indoor spawnery v městech.
- Nedávej zombie čich / blood scent.
- Nedělej 360° aggro.
- Nepiš „celý pack“ v jednom promptu. Děláš TICKETY níž, jeden po druhém.
- Neclaimuj, že JAR z CurseForge umíš vygenerovat. Módy se stahují. Ty píšeš overrides.

## 1. Repo layout (udržuj)

```
ASHLINE/
  manifest.json
  pack.toml                 # packwiz pokud použijete
  overrides/
    config/                 # finální configy
    kubejs/
    defaultconfigs/
    tacz/                   # gunpacky
    resourcepacks/ashline-infected/
    datapacks/ashline/
    config/ftbquests/
  docs/
  src/ashline-senses/       # vlastní mód
  src/ashline-factions/
  src/ashline-world/
```

Pack se skládá v CurseForge App / Packwiz:
- `minecraft` 1.20.1
- `modLoaders: forge 47.4.0` (nebo aktuální stable 47.3+)
- files[] = projectID + fileID z CurseForge

## 2. Pořadí práce (neměň)

### T0 — Boot skeleton
1. Packwiz nebo CurseForge custom pack.
2. Jen: Forge + performance stack + JEI + Jade + KubeJS.
3. Akceptace: svět se vytvoří, 60+ FPS menažerie, žádný crash.

### T1 — Create + Delight + TAN
4. Přidej Create strom (jeden elektro addon) + Farmer's Delight + Tough As Nails.
5. KubeJS: vypni vanilla tool progression, která kazí (netherite gate později).
6. Akceptace: napiješ se, uvaříš, postavíš water wheel.

### T2 — Lost Cities + In Control
7. Lost Cities. World type / profil: ruined modern, suburbs on edges.
8. In Control: 
   - žádný natural zombie spawn v blocích s stropem (indoor)
   - surface spawn jen `minecraft:zombie` a ASHLINE entity v capu
   - vypnout creepery, phantomy, wandering trader default
9. Akceptace: ve městě jsou struktury, v koupelně se nespawnuje mob když je místnost prázdná.

### T3 — TaCZ + Awareness
10. TaCZ + 1 gunpack + Sound Attracts Zombies + Zombie Awareness.
11. Config Awareness: `scent = false`, sound = true, light attract mírné.
12. Akceptace: výstřel táhne ulici. Crouch zezadu za walkerem ho neotočí (pokud vanilla AI to poruší → T5 senses mód).

### T4 — Visual infected
13. Resource pack + variant módy. Vanilla zelený Steve pryč z default spawnu.
14. Akceptace: 6+ vizuálních variant v malém městě.

### T5 — ashline-senses (vlastní mód)
Viz ticket v `cursor/PROMPTS.md`. Povinné API:
- `AshlineSenses.emitSound(level, pos, noise, radius, source)`
- item `ashline:pebble` 
- attachment / capability na structure piece `ClearedData`
- mixin na targeting: pokud target mimo FOV a žádný recent sound od targetu, aggro se nezakládá

Akceptace:
- [ ] pebble odláká
- [ ] výstřel radius > pebble
- [ ] motor IV radius > výstřel pistole
- [ ] zezadu ticho = no aggro
- [ ] vybitý supermarket remaining=0
- [ ] po unload/load remaining zůstane 0
- [ ] otevřená výloha + noc = 0–2 wanderers vejdou, ne 20 ze spawneru

### T6 — KubeJS ekonomika
Recepty z `03_RECIPES_MATERIALS.md`. Všechny vanilla easy gun recipes z TaCZ vypnout / přepsat na Create.

### T7 — FTB Quests
Strom z `04_QUESTS.md`. Čeština v popisech. ID stabilní (`ashline:q_...`).

### T8 — Reputation datapack + NPC
`05_NPC_FACTIONS.md`. Pokud Reputation 1.20.1 JAR není, ashline-factions mód.

### T9 — MineColonies + Recruits
Config: colony max early 5 citizens až quest `ashline:q_first_recruit`.  
Recruits hire cost napojený na standing.

### T10 — Immersive Vehicles
Wraky jako loot structures. Craft náhradních dílů Create. Palivo z rafinerie.

### T11 — Vlastní struktury
16 struktur z modlistu. Loot z `06_STRUCTURES_LOOT.md`. Processors položí infected count.

### T12 — Polish
PackMenu, default options (render dist 12, difficulty hard, reduced debug), credits, splash.

## 3. Config zákony (zapiš do overrides)

Zombie Awareness:
```
scentEnabled=false
soundEnabled=true
lightEnabled=false   # nebo velmi nízké; světlo nesmí nahradit zrak
tickRate=...
```

In Control spawn.jsonc logika:
- dimension overworld
- hasroof / light / structure tag `ashline:city_interior` → block spawn
- structure tag `ashline:city_street` → max 8 walkers / 64 block area

Lost Cities:
- explosion chance nízká
- city level dense
- prefab modern

MineColonies:
- raiders sladit s ASHLINE hordou, ne double siege
- food needs Delight items

TaCZ:
- default gun craft OFF
- durability ON
- headshot multiplier 2.0–2.5
- suppressor snižuje noise (hook senses)

IV:
- fuel consumption realistické
- engine sound event hooknout na AshlineSenses.emitSound každých N ticků

TAN:
- thirst drain +20 % ve wasteland
- impure water from city biomes

## 4. Akceptační run (60 minut QA)

1. Nový svět, hard, Lost Cities profil.
2. 10 minut předměstí: najdi vodu, jídlo, nůž/páčidlo.
3. Vejdi do domu: spočítej infected, vyčisti, odejdi, vrať se — stejný počet mrtvých, žádní noví uvnitř.
4. Vystřel bez tlumiče na ulici — přijdou z ulice, ne z vaničky.
5. Pebble otestuj.
6. Otevři JEI, ověř Create ammo recept.
7. Otevři quest book, kapitola 0 existuje.
8. Není waystone v JEI.
9. Žádný crash, žádný ticking entity exception.

Dokud T0–T5 neprojde, NEZAČÍNEJ heli a vzpouru.

## 5. Styl kódu

- Java 17, Forge 1.20.1 MDK, mappings official
- modid `ashline`, `ashline_senses`, atd.
- žádné system.out
- datapack JSON platné (json schema)
- KubeJS: `ServerEvents.recipes`, `ItemEvents.foodEaten` atd. syntax 1.20.1
- komentáře česky nebo anglicky, konzistentně EN v kódu, CZ v questech
