// Spoil timer via NBT ashline_fresh_until (world gameTime). canned_food never spoils.
const FRESH_TICKS = {
  meat: 24000,
  fruit: 72000,
  meal: 48000,
  jerky: 192000,
  canned: -1
}

function spoilDuration(id) {
  if (id === 'ashline:canned_food') return FRESH_TICKS.canned
  if (id.includes('jerky') || id.includes('dried')) return FRESH_TICKS.jerky
  if (id.includes('apple') || id.includes('bread') || id.includes('berry')) return FRESH_TICKS.fruit
  if (id.includes('delight') || id.includes('meal') || id.includes('stew')) return FRESH_TICKS.meal
  return FRESH_TICKS.meat
}

function stampFresh(stack, gameTime) {
  if (!stack || stack.empty) return
  if (stack.id === 'ashline:canned_food' || stack.id === 'ashline:spoiled_food') return
  if (!stack.hasTag('ashline:spoils') && !stack.hasTag('forge:foods') && stack.id.indexOf('farmersdelight:') !== 0) {
    if (stack.id.indexOf('minecraft:cooked_') !== 0 && stack.id.indexOf('minecraft:beef') !== 0
      && stack.id.indexOf('minecraft:pork') !== 0 && stack.id.indexOf('minecraft:chicken') !== 0
      && stack.id.indexOf('minecraft:mutton') !== 0 && stack.id.indexOf('minecraft:rabbit') !== 0
      && stack.id.indexOf('minecraft:cod') !== 0 && stack.id.indexOf('minecraft:salmon') !== 0
      && stack.id.indexOf('minecraft:bread') !== 0 && stack.id.indexOf('minecraft:apple') !== 0) {
      return
    }
  }
  let nbt = stack.nbt || {}
  if (nbt.ashline_fresh_until) return
  let dur = spoilDuration(stack.id)
  if (dur < 0) return
  stack.nbt = Object.assign(nbt, { ashline_fresh_until: gameTime + dur })
}

PlayerEvents.tick(event => {
  if (event.player.tickCount % 200 !== 0) return
  if (event.player.level.isClientSide()) return
  const inv = event.player.inventory
  const now = event.player.level.gameTime
  const size = inv.slots || inv.containerSize || 41
  for (let i = 0; i < size; i++) {
    let stack = inv.getStackInSlot ? inv.getStackInSlot(i) : inv.getItem(i)
    if (!stack || stack.empty) continue
    stampFresh(stack, now)
    let until = stack.nbt && stack.nbt.ashline_fresh_until
    if (until && now > until && stack.id !== 'ashline:spoiled_food') {
      inv.setStackInSlot ? inv.setStackInSlot(i, Item.of('ashline:spoiled_food', stack.count)) : inv.setItem(i, Item.of('ashline:spoiled_food', stack.count))
    }
  }
})
