# Copy-paste prompty do Cursoru

Pracuj vždy v repo ASHLINE. Po každém ticketu spusť akceptační body z bible. Nezačínej další ticket.

---

## PROMPT T5 — ashline-senses MDK

```
Vytvoř Forge 1.20.1 MDK projekt v src/ashline-senses.
modid: ashline_senses
version: 1.0.0
Java 17, official mappings.

Implementuj:
1. Item ashline_senses:pebble (stack 16). Po dopadu na blok nebo entitu zavolej
   SoundLureManager.emit(level, BlockPos, noise=35, radius=24, durationTicks=120, source=PLAYER).
2. SoundLureManager: server-side seznam lures. Každý tick infected s tagem ashline:infected
   v radiusu dostane WRAP goal InvestigateSoundGoal s prioritou vyšší než RandomStroll,
   nižší než HurtBy. Goal pathfinduje na lure pos, NE na hráče. Po duration idle.
3. Mixin / event LivingChangeTarget: pokud nový target je Player a
   !FovUtil.isInFov(mob, player, 100deg) && !SoundLureManager.playerMadeSoundNear(player, mob, 40)
   && mob.getLastHurtByMob() != player  → cancel target.
4. FovUtil.isInFov používá yRot mobu a vektor k hráči. Ignore pitch > 60.
5. Capability / SavedData BuildingTracker keyed by StructureStart chunkpos + structure id.
   Methods: addInfected, onDeathInBox, getRemaining, setSealed.
   Hook LivingDeathEvent pro entity tags ashline:infected.
6. Command /ashline remaining a /ashline seal
7. Config: fov, ranges, noise table JSON v data/ashline_senses/noise.json
8. Tag entity ashline:infected include minecraft:zombie, husk, drowned, zombie_villager
   + placeholder comments for extra mods.

Nepiš client GUI. Nepiš nové 3D modely pebble (použij stone button model).
Unit-level komentáře EN. Po vygenerování vypiš jak registrovat v mods.toml a jak otestovat.
```

---

## PROMPT T6 — KubeJS recepty

```
Jsi v ASHLINE pack overrides/kubejs. MC 1.20.1 Forge KubeJS 2001.
Čti docs/03_RECIPES_MATERIALS.md.
Napiš:
- startup_scripts/items.js : registrace itemů z tabulky surovin (textury placeholder)
- server_scripts/ammo.js : Create mechanical crafting / pressing / mixing pro TaCZ ammo IDs
- server_scripts/remove.js : remove default cheap TaCZ workbench recipes pokud id existují
- server_scripts/water.js : recipes dirty→boiled, filter
- server_scripts/spoil.js : Player tick každých 200t kontrola NBT fresh_until na food tags
- server_scripts/tags.js : item tags
Použij guard `if (Platform.isLoaded('create'))`.
Žádné syntaktické chyby. Komentář nahoře: které TaCZ item IDs musí packmaker ověřit v JEI.
```

---

## PROMPT T7 — FTB Quests

```
Vygeneruj FTB Quests SNBT/JSON strukturu pro FTB Quests 2001.4.x
do overrides/config/ftbquests/quests/chapters/
Kapitoly a id přesně podle docs/04_QUESTS.md.
Jazyk title/description: čeština.
Rewards: item ashline items nebo vanilla. Standing rewards jako command
`ashline standing add @s militia 80` s TODO pokud command ještě není.
Každý quest má ikonu. Závislosti acyclic. Chapter 7 locked until 1+3+seal.
```

---

## PROMPT T8 — Reputation datapack

```
Podle wiki Reputation módu The_Computerizer vytvoř datapack
overrides/datapacks/ashline/data/reputation/
6 frakcí z docs/05_NPC_FACTIONS.md.
Použij placeholder entity ids.
Přidej pack.mcmeta format 15.
Pokud schema neznáš přesně, stáhni strukturu z
https://github.com/AFunProject/Reputation/wiki a drž ji 1:1.
```

---

## PROMPT T2 — In Control

```
Napiš overrides/config/incontrol/spawn.json a loot.json.
Pravidla:
- zakázat spawn pokud block above is solid (indoor) AND biome/structure lostcities OR tag ashline:city
- povolit surface spawn infected cap
- zakázat creeper, phantom, witch default, wandering trader
- nether/end vanilla
Komentáře vysvětlují každé pravidlo.
```

---

## PROMPT T12 — PackMenu + defaults

```
Default options: difficulty hard, render 10-12, simulation 6, music 0.4,
autosave 5. Disable tutorial. Custom main menu title ASHLINE.
```
