# Cactusfix

![fabric](./assets/forge_64h.png)
[![modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/cactusfix)

Cactusfix is a mod for FabricMC, which provides multiple fixes for cacti.

<!-- TOC -->
* [Cactusfix](#cactusfix)
  * [Fixes](#fixes)
  * [FAQ](#faq)
  * [Updating the mod](#updating-the-mod)
<!-- TOC -->

## Fixes

Here is a table of all the fixes provided by Cactusfix:

| Fix name                           | Fix gamerule                | Default value |
|------------------------------------|-----------------------------|---------------|
| If cactus should despawn items     | `shouldCactusDamageItems`   | `false`       |
| If cactus should damage players    | `shouldCactusDamagePlayers` | `true`        |
| If cactus should be placed weirdly | `doBetterCactusPlacing`     | `false`       |

The fixes can be toggled with the help of `/gamerule`, if Cactusfix is installed on the server.

## FAQ

* ⛔ I do not allow reuploading of my mods
* ✅ I allow modpacks to use my mods\*

**\*Can I use this mod in my modpack?**

Yes! You are more than welcome to include my mod in your modpack, if you credit me properly, and make downloads go through my modrinth page.
I would also appreciate if you told me about it 😊.

**Plz updat to forge**

I won't update to forge, as I have never gotten it to work, and have no plan to do so.

**Will you update this to new MC versions?**

If I feel like it :)

**Where can I get in touch with you?**

Up-to-date contact information can be found at my [GitHub](https://github.com/Erb3/Erb3/blob/main/README.md).

## Updating the mod

> Note: This is how I work when I update the mod to new version. You as a player should not have to do this.

1. Create new branch
2. Update fabric.mod.json
3. Update gradle.properties
4. Patch for new release
5. Commit
6. Push & set main-branch to new
7. Build the jar
8. Publish to GitHub Releases
9. Publish to Modrinth