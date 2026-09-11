// Dirty city water → boiled / filtered. TAN thirst hooks after TAN is in the pack.
ServerEvents.recipes(event => {
  event.smelting('ashline:boiled_water_bottle', 'ashline:dirty_water_bucket')
  event.campfireCooking('ashline:boiled_water_bottle', 'ashline:dirty_water_bucket', 0.1, 200)
  event.shapeless('ashline:filter_paper', [
    'minecraft:paper',
    'minecraft:charcoal',
    'ashline:cloth'
  ])
  event.shapeless('ashline:filter_charcoal', [
    'ashline:filter_paper',
    'minecraft:charcoal'
  ])
  event.shapeless('ashline:clean_water_bottle', [
    'ashline:dirty_water_bucket',
    'ashline:filter_charcoal'
  ])
})

ItemEvents.foodEaten('ashline:spoiled_food', event => {
  event.player.potionEffects.add('minecraft:hunger', 200, 0)
})
