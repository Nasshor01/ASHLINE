# ASHLINE — Game Design Document

Verze dokumentu: 1.0  
Platforma: Minecraft Java 1.20.1 Forge  
Žánr: hardcore urban zombie survival + Create válečná ekonomika + frakční politika

---

## 1. Pitch

Svět spadl. Města stojí. Infikovaní v nich *jsou*, nerodí se ze spawneru.  
Jsi sám. Jídlo hnije. Voda z kohoutku tě zabije. Výstřel volá čtvrť.  
Až se stabilizuješ, stavíš fabriku, prodáváš zbraně, verbujíš lidi, létáš vrtulníkem na druhou elektrárnu.  
Když osadu vyždímáš, vezme si všechno zpátky.

---

## 2. Non-negotiable pravidla

1. Žádný indoor natural spawn v city strukturách.
2. Budova se dá vyčistit. Zůstane čistá, dokud ji neotevřeš díře / hordě.
3. Zombie nemají čich. Mají oči (FOV) a uši (radius zvuku).
4. Zombie zády k hráči + ticho = neotočí se.
5. Vanilla zombie skin není default. Infikovaný = mrtvý člověk v kontextu (hasič, civil, voják, crawler…).
6. Verbování je mid-game. Early = solo grind.
7. Žádný waystone / random teleport jako travel.
8. Create je ekonomika (munice, voda, palivo, jídlo), ne dekorace.
9. Spray-and-pray je validní, pokud máš munici. Stealth je default early.
10. Hráč nemůže mít všechny frakce rády.

---

## 3. Win / lose

Není jeden konec. Stavy světa:

- **Stabilizovaný dům** — žiješ.
- **Město-stát** — proud, zdi, osada, daně.
- **Vazal Militia** — bezpečí za poslušnost.
- **Král Cutu** — zboží, zrada.
- **Svatý Ward** — lidi žijí, ty dlužíš krev.
- **Pád** — vzpoura / horda / prázdná nádrž uprostřed wastelandu.

Prohra = smrt s infekcí, ztráta osady, ztráta jediného stroje daleko od base.

---

## 4. Survival sloupce

| Sloupec | Pravidlo |
|---|---|
| Hlad | jídlo má trvanlivost; konzervy > čerstvé |
| Žízeň | městská voda špatná; vaření / filtr / déšť / studna |
| Únava | nespavost = horší aiming a hlasitější chůze (pokud to mód unese, jinak jen Tough As Nails teplota) |
| Teplota | zima žere kalorie; wasteland pálí / sálá |
| Infekce | kousnutí = timer + vyšší žízeň/hlad; smrt v infekci = vstaneš jako infected |
| Morálka osady | hlad + strach + nespravedlnost → vzpoura |

---

## 5. Detekce infected

### Zrak
- Kužeľ ~90–110° dopředu
- Dosah: 16–24 bloků den, 8–12 noc, 4–8 tma + crouch
- Sprint / skok zvětšuje siluetu
- Zezadu / za plnou zdí = nevidí

### Sluch
- Každá akce má `noise` 0–100 a radius v blocích
- Zombie jde ke **zdroji zvuku**, ne k entitě hráče
- Po 4–8 s bez vizuálu se vrací do idle
- Distrakce: `ashline:pebble`, prázdná konzerva, granát, výstřel, motor

### Zakázáno
- blood scent
- 360° aggro
- spawn wave „protože jsi ve městě“
- chain aggro celé mapy z jednoho pohledu

Chain: runner tě vidí → běží / řve (zvuk) → *jen* infected v doslechu toho zvuku investigují.

---

## 6. Clear / Seal

Každá struktura s ID (supermarket, panelák vchod, škola, Nemocnice křídlo A):

```
remaining_infected: int
sealed: bool
breaches: [door, window, hole]
```

- `remaining == 0` a `sealed == true` → forward base, žádný indoor respawn
- `remaining == 0` a `sealed == false` → wanderer z ulice může vejít
- hnízda (morgue, kanál, lab) jsou výjimka — viditelná, zničitelná

Unloaded chunk ukládá čísla, nespawnuje 200 entit.

---

## 7. Frakce

| ID | Jméno | Chce | Dává | Nesnáší |
|---|---|---|---|---|
| militia | Ashline Militia | pořádek, daň munice/palivo | zbraně, checkpointy, eskorta | Cut, nelegální zbrojovky |
| cut | The Cut | zboží, zbraně, dluhy | černý trh, info, šrot | Militia, Ward etika |
| ward | White Ward | vzorky, krev, živé inf. | léky, antirad, vakcína | Choir, chaos |
| parish | Iron Parish | kov, schéma, uhlí | Create recepty, dílny | radiace, anarchii |
| holds | Free Holds | jídlo, bezpečí | pracovní síla, farmy | vysoké daně, Choir |
| choir | Null Choir | šíření nákazy / „evoluci“ | sabotáže, hordy | Ward, Militia |

Standing −1000..+1000 per frakce.  
Prah: −400 hostile on sight, −100 odmítá trade, 0 neutrál, +200 trade+, +500 verbování/elita, +800 přístup do HQ.

---

## 8. Osada

Early: 0 NPC.  
Mid lock (všechny tři):
- stálá čistá voda
- jídlo ≥ 3 dny zásoby
- base přežila 1 noční tlak bez collapse

Pak 1 rekrut. Ne armáda.

Vzpoura: mood < práh 3 dny → krádež → otevřená brána → odchod k Cut / lynč.

MineColonies = práce a stavby.  
Recruits = ozbrojený doprovod.  
Nesmí se slévat v jednu AI.

---

## 9. Vozidla

Žebřík: nohy → kolo → buggy → dodávka → obrněnec → vlak → heli → letadlo.  
Vrak na mapě, ne creative. Motor = hluk. Palivo finite.

---

## 10. Zóny mapy

Předměstí, malé město, velkoměsto, průmysl, energetika, zdravotnictví, military, wasteland, NPC pocket.

Lost Cities = urban fabric.  
Vlastní struktury = nemocnice, elektrárna, military, depo, škola, benzinka, supermarket (pokud Lost Cities nemá dostatek variant).
