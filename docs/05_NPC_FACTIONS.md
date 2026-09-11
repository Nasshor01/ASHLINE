# ASHLINE — NPC, obchody, reputace, vzpoura

## 1. Datapack Reputation

Cesta: `datapacks/ashline/data/reputation/factions/`  
(Pokud mód používá `data/reputation/` — držet wiki strukturu.)

Každá frakce JSON:
- id, display, icon
- members: entity IDs
- enemies: other faction ids
- currency item
- thresholds
- weights: kill_member, loot_chest, save_member, trade, complete_contract

## 2. Entity sady (použij Recruits + TACZ NPCs + villagers + custom)

### Militia
- `ashline:militia_grunt` (TACZ NPC rifle, helmet)
- `ashline:militia_officer`
- checkpoint villager trader `ashline:militia_quartermaster`

Trade (standing ≥ 0):
- 16 canned → 8x 9mm
- 4 gasoline → 1 bandage kit
- standing ≥ 200: service pistol
- standing ≥ 500: AR + ammo box
- standing < −400: shoot on sight

Daň na silnici: 8 ammo nebo 1 gasoline. Odmítnutí → −80 standing, případně firefight.

### Cut
- `ashline:cut_dealer`
- `ashline:cut_enforcer` (shotgun)
- bazaar v kanále / skladu

Trade vždy, ceny horší při low standing:
- zbraně výkup (hráč prodává)
- info (mapa struktury)
- tire / battery
- standing ≥ 300: hire 1 enforcer (Recruits convert)
- po `q_sell_guns_cut` opakovaně: chance raid na osadu

### Ward
- `ashline:ward_medic`
- `ashline:ward_orderly` (hostile if trespass lab)

Trade:
- 8 rotten_flesh NBT sample → antibiotics
- live capture quest
- antirad
- standing ≥ 500: vaccine component

### Parish
- `ashline:parish_foreman`
- workshop u industriálu

Trade:
- Create schematic fragments
- brass / steel
- odemkne KubeJS hidden recipes přes quest + standing

### Free Holds
- MineColonies colonists + village-like
- food in, protection out
- recruit workers

### Choir
- `ashline:choir_preacher` (neobchoduje)
- `ashline:choir_thrall`
- nest structure
- standing jen dolů, pokud nespálíš nest (pak Militia + Ward +)

## 3. Kontrakty (ashline-factions nebo FTB + command)

| ID | Zadavatel | Úkol | Rep delta |
|---|---|---|---|
| c_escort | militia | doprovod konvoje 400 bloků | mil +60 |
| c_ammo | militia | 64x 556 | mil +40 |
| c_smuggle | cut | převez zbraně přes checkpoint | cut +80 mil −120 |
| c_hit | cut | zabij militia officer | cut +100 mil −250 |
| c_sample | ward | 3 hospital samples | ward +70 |
| c_power | parish | dovez 16 brass + coal | parish +50 |
| c_grain | holds | 32 bread/rice | holds +40 |
| c_purge | militia | burn choir nest | mil+ ward+ choir− |

## 4. Verbování

Podmínky (`q_first_recruit` + beds + food):
- Holds civilian → worker (MineColonies hire)
- Militia deserter → Recruits soldier, mil −30
- Cut enforcer → soldier, mood penalty osady
- Ward medic → colonist healer, chce lab block

Max early: 3. Mid: 12. Late: colony cap.

## 5. Vzpoura (osada)

Každý den tick (server):
```
mood = clamp(
  +food_days * 10
  +safety (no breach 3 days) * 15
  +fair_tax
  -overwork
  -unpaid_soldiers
  -choir_presence
  -hunger
  -recent_casualties
, 0, 100)
```

- mood < 25 tři dny: event `theft`
- mood < 15: `gate_open_night`
- mood < 10: `coup` — Recruits hostile, warehouse loot stolen, town hall fire

Prevence: feast (Delight meal mass), lower tax, execute Choir agent (risk Holds −).

## 6. Dialog

FTB quest + kniha + Reputation chat icons.  
Nedělej pleno voiced RPG. 2–4 věty na NPC.
