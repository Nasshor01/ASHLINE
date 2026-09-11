// ASHLINE startup items — placeholder registrations
// Textures: kubejs/assets/ashline/textures/item/<id>.png (missing = purple/black is OK until art)
// TaCZ IDs to verify in JEI after install: tacz:9mm tacz:45acp tacz:12g tacz:556x45 tacz:762x39 tacz:308

StartupEvents.registry('item', event => {
  const stack16 = new Set(['pebble'])
  const simple = [
    'scrap', 'circuit_scrap', 'cloth', 'filter_paper', 'bandage',
    'antibiotics', 'antirad', 'canned_food', 'spoiled_food',
    'lead_ingot', 'brass_casing_ammo', 'primer', 'empty_can',
    'pebble', 'battery_dead', 'battery_charged', 'tire',
    'engine_block_scrap', 'schematic_fragment',
    'faction_chit_militia', 'faction_token_cut', 'faction_vial_ward',
    'filter_charcoal', 'gunpowder_dust', 'dirty_bandage',
    'gasoline_can', 'diesel_can', 'avionics_fuel_can',
    'crowbar', 'pipe_pistol_kit'
  ]
  simple.forEach(id => {
    event.create(`ashline:${id}`).maxStackSize(stack16.has(id) ? 16 : 64)
  })

  event.create('ashline:dirty_water_bucket').maxStackSize(1)
  event.create('ashline:boiled_water_bottle').maxStackSize(16)
  event.create('ashline:clean_water_bottle').maxStackSize(16)
})
