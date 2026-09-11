# ASHLINE — FTB Quests strom

Jazyk popisků: čeština.  
ID prefix: `ashline:`.  
Kapitoly lockují se navzájem.  
Odměny malé (jídlo, mapa, 1 nástroj), ne diamanty.

---

## Kapitola 0 — Probuzení (auto)

- `q_wake` — otevři quest book  
- `q_thirst` — vypij čistou / převařenou vodu  
- `q_food` — sjez jídlo  
- `q_blade` — vyroť nůž nebo crowbar  
- `q_hide` — přežij první noc v zavřeném domě  

Odměna: 4 pebble, 1 bandage, mapa předměstí (paper).

---

## Kapitola 1 — Stabilizace

- `q_filter` — vyrob filtr / boil 8 vod  
- `q_cans` — získej 8 konzerv  
- `q_baricade` — zabedni 4 okna (detect placed planks on structure)  
- `q_loot_gas` — vyčisti benzinku (kill count v bounding box NEBO check structure remaining=0)  
- `q_scrap_16` — 16 scrap  

Odměna: charcoal filter, empty jerry can.

---

## Kapitola 2 — Sluch

- `q_pebble` — použij pebble  
- `q_stealth_kill` — zabij infected aniž bys vystřelil  
- `q_suppressed` — získej / vyrob tlumič  
- `q_loud_mistake` — vystřel a přežij (optional fail-flavor)

Odměna: 12x 9mm.

---

## Kapitola 3 — První město

- `q_enter_city` — vstup do Lost Cities biomu  
- `q_super_clear` — remaining=0 supermarket  
- `q_seal` — zabedni výlohy  
- `q_police` — loot police station  
- `q_hospital_wing` — přežij křídlo A (ne nutně clear celé nemocnice)

Odměna: backpack upgrade / medical kit.

---

## Kapitola 4 — Železo a soukolí (Create)

- `q_waterwheel`  
- `q_press`  
- `q_mixer`  
- `q_brass`  
- `q_ammo_line` — vyrob 32 nábojů na lince  
- `q_train_rail` — volitelné Steam n Rails

Odměna: Parish standing +80 (command), schematic fragment.

---

## Kapitola 5 — Lidé na mapě

- `q_meet_militia` — trade na checkpointu  
- `q_meet_cut` — bazaar  
- `q_meet_ward` — odevzdej 1 sample (rotten flesh special NBT)  
- `q_meet_parish`  
- `q_meet_holds`  
- `q_radio` — slyš 3 relace (item use)

Odměna: faction note (lore kniha).

---

## Kapitola 6 — První stroj

- `q_tires` — 4 tire  
- `q_battery` — charged battery  
- `q_buggy` — used IV vehicle  
- `q_fuel_8` — 8 gasoline  
- `q_drive_1k` — ujedi 1000 bloků (stat)

Odměna: diesel can, map fragment druhého města.

---

## Kapitola 7 — Osada

Lock: voda + 24 konzerv + `q_seal` done.

- `q_townhall` — polož MineColonies town hall  
- `q_beds_4`  
- `q_cook`  
- `q_guard` — 1 Recruits hire  
- `q_walls`  
- `q_tax` — rozhodni daň (choice quest)

Odměna: standing Holds +100.

---

## Kapitola 8 — Špinavá práce

Choice questy (jedna větev zamyká druhou částečně):

- `q_sell_guns_cut` — předej 64 ammo Cut → Militia −150, Cut +200  
- `q_militia_tax` — zaplať daň → Militia +120, Cut −50  
- `q_ward_live_sample` — rizikový quest nemocnice  
- `q_choir_burn` — spálit nest ( Choir −400 )

---

## Kapitola 9 — Druhé město / wasteland

- `q_antirad`  
- `q_hazmat`  
- `q_plant` — elektrárna navštívena  
- `q_core_schematic`  
- `q_heli_parts`  
- `q_heli_flight`  
- `q_ultimate` — zabij pojmenovaného bosse v lab/reaktor

---

## Kapitola 10 — Koruna nebo popel

Ending flags (FTB quest completion):

- `end_citystate` — proud + 25 kolonistů + walls  
- `end_vassal` — militia ≥ 700  
- `end_kingpin` — cut ≥ 700 a vlastní zbrojovka  
- `end_saint` — ward ≥ 700 a vakcína item  
- `end_ash` — osada destroyed (detect / command)

---

## Task typy které použít

- item
- kill (specific entity tags `ashline:infected`)
- visit biome / structure
- location
-checkmark
- custom KubeJS `FTBQuests.addCustomReward` standing
- observation (craft type)

Žádný „snes 64 diamond“.
