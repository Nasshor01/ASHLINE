# ASHLINE factions datapack (custom schema)

Path: `data/ashline/factions/<id>.json`

Reputation-mod wiki schema is unknown / JAR unavailable — this is an **ASHLINE custom** structure.
`ashline-senses` already implements runtime standing via SavedData + commands:

```
/ashline standing add <players> <faction> <amount>
/ashline standing get <players> <faction>
/ashline standing set <players> <faction> <amount>
```

Factions: `militia`, `cut`, `ward`, `parish`, `holds`, `choir`  
Range: **-1000 … 1000** (clamped).

A future `ashline-factions` mod can load these JSON files for NPC trades, contracts, and mood.
