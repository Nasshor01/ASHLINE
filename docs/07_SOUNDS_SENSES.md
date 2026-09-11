# ASHLINE — tabulka hluku

`noise` 0–100. `radius` v blocích. Zombie investiguje, pokud `distance <= radius` a má volný sluch tick.

| Zdroj | noise | radius | poznámka |
|---|---|---|---|
| crouch step | 4 | 4 | |
| walk | 12 | 10 | |
| sprint | 28 | 18 | |
| jump land | 22 | 14 | |
| break glass | 55 | 36 | |
| break wood | 30 | 20 | |
| place block | 16 | 12 | |
| chest open | 18 | 12 | |
| crafting / quiet | 8 | 6 | |
| Create press / mill | 40 | 28 | per machine running |
| Create steam engine | 60 | 40 | |
| pebble | 35 | 24 | fake source 6 s |
| empty can throw | 38 | 26 | |
| bow | 20 | 14 | |
| suppressed pistol | 32 | 22 | |
| pistol | 70 | 48 | |
| shotgun | 85 | 56 | |
| rifle | 88 | 64 | |
| explosion / grenade | 100 | 80 | |
| IV buggy engine idle | 50 | 32 | repeating |
| IV buggy drive | 72 | 48 | |
| armored | 80 | 56 | |
| helicopter | 100 | 96 | celá čtvrť |
| plane | 100 | 120 | |
| scream special | 75 | 40 | chain |
| cannon CBC | 100 | 100 | |

FOV default walker: 100°, range 20 den / 10 noc.  
Runner: 120°, range 24 / 14, speed 1.25×.  
Crawler: 80°, range 8, height 0.6.  
Specials: dle typu, nikdy 360°.

Pebble item: throw, on land emitSound, no damage.
