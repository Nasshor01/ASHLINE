// Remove default cheap TaCZ workbench recipes once IDs are dumped.
// After first boot with TaCZ: /kubejs dump recipes  → replace this list.
ServerEvents.recipes(event => {
  const maybe = [
    'tacz:gun_smith_table',
    'tacz:modern_kinetic_gun',
    'tacz:ammo_9mm',
    'tacz:ammo_45acp',
    'tacz:ammo_12g',
    'tacz:ammo_556x45',
    'tacz:ammo_762x39',
    'tacz:ammo_308'
  ]
  maybe.forEach(id => {
    try { event.remove({ id: id }) } catch (e) {}
  })
  try { event.remove({ mod: 'tacz', type: 'minecraft:crafting_shaped' }) } catch (e) {}
  try { event.remove({ mod: 'tacz', type: 'minecraft:crafting_shapeless' }) } catch (e) {}
})
