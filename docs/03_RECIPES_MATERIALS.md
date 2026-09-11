# ASHLINE — materiály a recepty

Princip: early scrap ručně. Mid Create. Late linka. Žádný iron-press-do-assault-rifle na stole.

---

## 1. Suroviny

| ID | Získání | Použití |
|---|---|---|
| `ashline:scrap` | wraky, auta, spotřebiče, 2–6 z wreckage | vše early |
| `ashline:circuit_scrap` | elektronika, radio tower | rádio, IV palubní deska |
| `ashline:dirty_water_bucket` | městská voda, řeky ve city | nelze pít |
| `ashline:clean_water_bottle` | uvařit / filtrovat | žízeň |
| `ashline:filter_paper` | papír + uhlí + látka | vodní filtr |
| `ashline:cloth` | gauče, závěsy, zombie drop vzácně | obvazy, filtry |
| `ashline:bandage` | cloth + alkohol / vodka delight | krvácení |
| `ashline:antibiotics` | Ward trade / lab loot / late craft | infekce stage 1–2 |
| `ashline:antirad` | Ward / lab / Create late | wasteland |
| `ashline:canned_food` | supermarket, 14 dní spoil timer vypnutý (konzerva) | osada |
| `ashline:spoiled_food` | po expiraci čerstvého | kompost, ne jíst |
| `ashline:lead_ingot` | Create crush galena / loot shooting range | projektily |
| `ashline:brass_casing_ammo` | Create brass | nábojnice |
| `ashline:gunpowder_dust` | vanilla + Create mill |
| `ashline:primer` | Parish recept / military loot | náboj |
| `ashline:empty_can` | po snězení konzervy | pebble-tier lure, craft |
| `ashline:pebble` | 1 cobble → 4 pebble | distrakce |
| `ashline:gasoline_bucket` | benzinka loot finite / late rafinerie | auta |
| `ashline:diesel_bucket` | depo, military, rafinerie | obrněnec, generátor |
| `ashline:avionics_fuel` | late rafinerie + Parish | heli / letadlo |
| `ashline:battery_dead` | wraky | |
| `ashline:battery_charged` | Create charge | startér auta, rádio |
| `ashline:tire` | wraky | buggy |
| `ashline:engine_block_scrap` | wraky | IV oprava |
| `ashline:faction_chit_militia` | odměna quest | trade |
| `ashline:faction_token_cut` | |
| `ashline:faction_vial_ward` | |
| `ashline:schematic_fragment` | lab, parish | odemyká Create recepty |

Vanilla iron/copper/zinc z Create zůstávají. Netherite = wasteland rare, ne mine.

---

## 2. Voda

```
dirty_water + campfire 200t → boiled_water (žízeň OK, ruměnec 5 %)
dirty_water + ashline:charcoal_filter (Create item) → clean_water
rain collected in barrel (KubeJS block nebo Delight kompat) → clean
```

Městský biome tag `ashline:contaminated_water`: source water dá dirty.

---

## 3. Jídlo a spoil

KubeJS NBT `ashline_fresh_until` (world tick).  
Kategorie:
- raw / cooked meat: 1–2 dny
- fruit / bread: 3 dny
- Delight meals: 2 dny
- `canned_food`: nehnije
- sušené maso (smoking + salt): 8 dní

Po expiraci item transform na `spoiled_food`. Jíst = hunger + thirst penalty.

---

## 4. Munice (Create povinné od mid)

Early (crafting table, drahé, low yield):
```
pipe gun (Pipe Guns) = scrap + copper + stick
9mm x4 = brass nugget + gunpowder + lead nugget   // nouzovka
```

Mid (Create mixer + press + deployer):
```
brass sheet → casing
lead block crush → bullets
gunpowder + redstone trace → powder grain
deployer: casing + powder + bullet + primer → 8x ammo
```

Typy napojit na TaCZ IDs:
- `tacz:9mm`
- `tacz:45acp`
- `tacz:12g`
- `tacz:556x45`
- `tacz:762x39`
- `tacz:308`

Každý vyšší ráže = víc brass + víc powder.  
Linka 556 je quest Parish `q_parish_ammo_line`.

Tlumič: iron sheet + cloth + bottle = suppressor item TaCZ attachment, craft lock quest.

---

## 5. Zbraně gate

Vypni default TaCZ workbench cheap recipes (TaCZ JS / KubeJS).

| Zbraň | Gate |
|---|---|
| nůž / crowbar | scrap + iron |
| pipe pistol | scrap line |
| service pistol | military loot OR Parish + 20 9mm crafted |
| shotgun | loot police OR Create steel + pipes |
| AR / AK | military base clear OR zbrojovka quest + standing militia≥200 OR cut≥300 |
| sniper | late lab / military |
| CBC cannon | Create steel + colony walls |

---

## 6. Palivo

```
Create mixer: bioethanol (Delight corn/beet) → weak fuel (buggy 50 %)
TFMG/New Age destilace: crude (loot barrels) → gasoline / diesel
avionics = diesel + Parish catalyst + electricity
```

Benzinka chest: 1–4 cans, **ne infinite**.

---

## 7. Medicína

```
cloth x2 + boiled_water = dirty bandage (50 %)
bandage + alcohol (Respite / potion) = bandage
lab loot: antibiotics
Create mixer late: antibiotics recipe po quest Ward
antirad: iron + glowberry analog + Ward vial
```

---

## 8. Vozidla díly

IV native craft přepsat na:
```
engine = engine_block_scrap + steel + battery_charged + Parish
tire = rubber (Create) + scrap
panel = steel sheets
```

Wrak structure loot: 0–1 tire, 0–1 dead battery, 0–2 scrap, 5 % engine.

---

## 9. Numismatics / měna

Osada používá Create coins až mid. Early barter item-for-item.  
Frakční žetony neskládej do jednoho emerald ekonomiky.
