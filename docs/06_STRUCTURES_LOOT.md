# ASHLINE — struktury a loot

## Pravidlo loot tabulky

Každá budova má **adresu**. Stejný item není všude.

| Struktura | Infected preplace | Loot jádro | Rare |
|---|---|---|---|
| suburb house | 0–3 | food 20 %, cloth, junk | 2 % pistol |
| apartment stairwell | 4–12 | junk, 1 can | keys lore |
| gas station | 3–8 | gasoline 1–4, snacks, map | battery 15 % |
| supermarket | 20–60 | cans, water dirty, empty cans | ammo 10 %, med 8 % |
| school | 10–25 | cloth, paper, food | radio 5 % |
| police | 8–20 | 9mm, 12g, baton, vest | shotgun 15 %, keys armory |
| hospital wing | 15–40 | bandage, samples, beds | antibiotics 12 %, special |
| hospital morgue | 8–15 + 1 special | samples | nest flag |
| warehouse | 6–18 | scrap, circuits, storage | engine 8 %, tire 20 % |
| depot / rails | 8–20 | diesel, steel, rails | IV parts |
| power plant | 20–50 + radiation | copper, circuits | schematic core |
| military checkpoint | 6–12 living OR infected | ammo, chits | pass lore |
| military base | 40–80 | guns, diesel, armor | heli crate 4 % |
| airfield | 10–30 | avionics fuel rare | plane crate 2 % |
| radio tower | 2–8 | circuit, battery | radio item |
| lab | 12–30 + specials | schematics, vaccine parts | ultimate trigger |
| faction outposts | 0 infected indoor (living NPC) | faction currency | contracts |
| choir nest | specials | cursed loot | preacher |
| wasteland farm | 2–10 glowing | antirad 5 % | |
| wreck car (mini) | 0–1 crawler | scrap, tire chance | |

Chest count: 2–8 na strukturu, Lootr pokud MP.

## Processors

Structure processor `ashline:infect_interior`:
- na značky `jigsaw` / structure block data `infected_spot` polož entity
- počet z tabulky ±30 %
- žádný mob spawner block

`ashline:loot_address` nastav loot table podle struktury.

## Clear detekce

Bounding box struktury po smrti entity s tagem `ashline:infected` uvnitř → decrement.  
Seal: hráč položí `ashline:barricade` nebo planks na tagged window positions (≥80 % openings closed).

## Worldgen placement

- Lost Cities vyplní urban grid
- ASHLINE structures: rarity
  - gas station: frequent suburbs
  - supermarket: every 1–2 city districts
  - hospital: 1 per large city
  - power plant: 1 per 3–5k blocks industrial
  - military base: rare 6k+
  - airfield: rare
  - faction outposts: 1–2k along roads
