# ASHLINE Senses (`ashline_senses`)

Forge 1.20.1 MDK. Java 17. Official mappings. Forge 47.4.0.

## Build (JDK 17)

Gradle wrapper is included. From this folder:

```
gradlew.bat build
```

JAR: `build/libs/ashline-senses-1.0.0.jar` → copy into the CurseForge instance `mods/`.

First build downloads Forge + mappings (needs network, several minutes).

## Public API

```java
AshlineSenses.emitSound(level, pos, noise, radius, source);
AshlineSenses.getStanding(server, uuid, FactionId.MILITIA);
AshlineSenses.addStanding(server, uuid, FactionId.CUT, 200);
```

## Commands

```
/ashline remaining
/ashline seal | unseal
/ashline standing add <players> <faction> <amount>
/ashline standing get <players> <faction>
/ashline standing set <players> <faction> <amount>
```

Factions: `militia`, `cut`, `ward`, `parish`, `holds`, `choir` (clamped −1000…1000).  
FTB Quests use e.g. `ashline standing add @s parish 80`.

## In-game checks (T5)

- Throw pebble: infected path to impact, not to you.
- Unsilenced shot hooks `emitSound` (pistol 70/48).
- Crouch behind walker, no recent sound, not last-hurt → no aggro.
- `/ashline remaining` and `/ashline seal` inside a structure.
- Unload/reload chunk: remaining stays.

Item id: `ashline_senses:pebble` (KubeJS also has `ashline:pebble` placeholder until merged).
