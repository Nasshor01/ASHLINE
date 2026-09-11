ServerEvents.tags('item', event => {
  event.add('ashline:spoils', [
    'minecraft:cooked_beef',
    'minecraft:cooked_porkchop',
    'minecraft:cooked_chicken',
    'minecraft:cooked_mutton',
    'minecraft:cooked_rabbit',
    'minecraft:cooked_cod',
    'minecraft:cooked_salmon',
    'minecraft:beef',
    'minecraft:porkchop',
    'minecraft:chicken',
    'minecraft:mutton',
    'minecraft:rabbit',
    'minecraft:cod',
    'minecraft:salmon',
    'minecraft:bread',
    'minecraft:apple',
    'minecraft:carrot',
    'minecraft:potato'
  ])
  event.add('ashline:lures', ['ashline:pebble', 'ashline:empty_can', 'ashline_senses:pebble'])
  event.add('ashline:faction_tokens', [
    'ashline:faction_chit_militia',
    'ashline:faction_token_cut',
    'ashline:faction_vial_ward'
  ])
})

ServerEvents.tags('worldgen/biome', event => {
  event.add('ashline:contaminated_water', ['minecraft:plains', 'minecraft:river'])
})
