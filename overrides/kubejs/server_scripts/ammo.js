// ASHLINE ammo — VERIFY these TaCZ item IDs in JEI after tacz is installed:
// tacz:9mm, tacz:45acp, tacz:12g, tacz:556x45, tacz:762x39, tacz:308
// Create recipe types need Create loaded.

ServerEvents.recipes(event => {
  event.shapeless('4x ashline:pebble', ['minecraft:cobblestone'])
  event.shapeless('ashline:crowbar', ['ashline:scrap', 'ashline:scrap', 'minecraft:iron_ingot'])
  event.shapeless('ashline:pipe_pistol_kit', ['ashline:scrap', 'minecraft:copper_ingot', 'minecraft:stick'])

  event.shapeless('ashline:dirty_bandage', ['ashline:cloth', 'ashline:cloth', 'ashline:boiled_water_bottle'])
  event.shapeless('ashline:bandage', ['ashline:dirty_bandage', 'minecraft:potion'])

  if (!Platform.isLoaded('create')) return

  event.recipes.createPressing('4x ashline:brass_casing_ammo', '#forge:plates/brass')
  event.recipes.createMilling('ashline:gunpowder_dust', 'minecraft:gunpowder')

  // Emergency 9mm — bad yield, crafting table
  event.shapeless('2x tacz:9mm', [
    'ashline:brass_casing_ammo',
    'minecraft:gunpowder',
    'ashline:lead_ingot'
  ])

  event.recipes.createMixing('8x tacz:9mm', [
    '4x ashline:brass_casing_ammo',
    '2x minecraft:gunpowder',
    'ashline:lead_ingot',
    'ashline:primer'
  ]).heated()

  event.recipes.createMixing('6x tacz:45acp', [
    '4x ashline:brass_casing_ammo',
    '3x minecraft:gunpowder',
    'ashline:lead_ingot',
    'ashline:primer'
  ]).heated()

  event.recipes.createMixing('4x tacz:12g', [
    '2x ashline:brass_casing_ammo',
    '4x minecraft:gunpowder',
    '2x ashline:lead_ingot',
    'ashline:primer'
  ]).heated()

  event.recipes.createMixing('8x tacz:556x45', [
    '6x ashline:brass_casing_ammo',
    '4x minecraft:gunpowder',
    '2x ashline:lead_ingot',
    'ashline:primer'
  ]).heated()

  event.recipes.createMixing('6x tacz:762x39', [
    '6x ashline:brass_casing_ammo',
    '5x minecraft:gunpowder',
    '2x ashline:lead_ingot',
    'ashline:primer'
  ]).heated()

  event.recipes.createMixing('4x tacz:308', [
    '6x ashline:brass_casing_ammo',
    '6x minecraft:gunpowder',
    '3x ashline:lead_ingot',
    'ashline:primer'
  ]).heated()

  event.recipes.createMixing('ashline:battery_charged', [
    'ashline:battery_dead',
    'minecraft:redstone'
  ]).heated()
})
