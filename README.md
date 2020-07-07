# LibGDX freetype skin

This library extends LibGDX asset management with [`FreeTypeSkin`](src/main/java/com/github/acanthite/gdx/graphics/g2d/FreeTypeSkin.java) and [`FreeTypeSkinLoader`](src/main/java/com/github/acanthite/gdx/graphics/g2d/FreeTypeSkinLoader.java) classes, which allow you to specify TTF fronts right in the `uiskin.json` file. You don't need to pack bitmap fonts manually and specify them [via skin parameter](https://stackoverflow.com/a/39174630/13202054).
Just drop some `.ttf` into your assets folder, set parameters and done.

## Dependencies
To use freetype-skin in your project just add the jipack repo and the freetype-skin dependency
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.acanthite:freetype-skin:0.2'
}
```

## Simple usage
You will need to load your uiskin.json via `FreeTypeSkin` and pass it to where you needed a `Skin`, just like you would do this with standard `Skin`. 

```libgdxjson
{
  BitmapFont: {
    default: {
      file: myfont.ttf,
      size: 14
      // other font properties (see the parameters section)
    }
    small: {
      file: myfont.ttf,
      size: 10
      // other font properties ...
    }
  }
  // other styles ...
}
```
Then load it and use it:
```java
Skin skin = new FreeTypeSkin(Gdx.files.internal("assets/uiskin.json"));
Window window = new Window("Some Title", skin);
```


**Note**: the `skin` variable is defined as `Skin` and has assigned a `FreeTypeSkin`. That is, `FreeTypeSkin` is subclass of `Skin`, and you can freely pass it where `Skin` is required. It will work just as you expect.

### Using with `AssetManager`
If you want to use it with `AssetManager` you need to replace the default asset loader for `Skin.class`. The rest is done as usual:
```java
AssetManager manager = new AssetManager();

// replace the builtin skin loader with FreeTypeSkinLoader
manager.setLoader(Skin.class, new FreeTypeSkinLoader(manager.getFileHandleResolver()));

// queue skin loading
manager.load(Skin.class, "assets/uiskin.json");
manager.finishLoading();

// and use it
Skin skin = manager.get("assets/uiskin.json", Skin.class);
Window window = new Window("Some Title", skin);
```
## Font (FreeType) parameters

To define bitmap fonts in a skin file, you _must_ specify TTF font via `file` _and_ font size via `size` properties. This is the required min minimum.

Other than that, you can specify any other property from [FreeTypeFontParameter](https://github.com/libgdx/libgdx/blob/48877d97317ca8063b9bf4479d3c253db417677a/extensions/gdx-freetype/src/com/badlogic/gdx/graphics/g2d/freetype/FreeTypeFontGenerator.java#L740) class, except the [`packer`](https://github.com/libgdx/libgdx/blob/48877d97317ca8063b9bf4479d3c253db417677a/extensions/gdx-freetype/src/com/badlogic/gdx/graphics/g2d/freetype/FreeTypeFontGenerator.java#L778) and the [`incremental`](https://github.com/libgdx/libgdx/blob/48877d97317ca8063b9bf4479d3c253db417677a/extensions/gdx-freetype/src/com/badlogic/gdx/graphics/g2d/freetype/FreeTypeFontGenerator.java#L792).

Any omitted property will simply default to its value as specified in `FreeTypeFontParameter`.

Here is an example with some advanced properties:
```libgdxjson
{
  Color: {
    black: { a: 1, b: 0, g: 0, r: 0 },
    white: { a: 1, b: 1, g: 1, r: 1 },
    skyBlue: { a: 1, b: 0.922, g: 0.808, r: 0.529 }
  }, 
  BitmapFont: {
    title: {
      // these are required
      file: myfont.ttf,
      size: 36,

      // these are optional
      color: skyBlue,
      borderColor: black,
      borderWidth: 2,
      shadowColor: black,
      shadowOffsetX: 3,
      shadowOffsetY: 3,
    }
  }
}
```

## Vis UI Support?
Yes! It works out of the box. As it was said earlier, `FreeTypeSkin` is a `Skin`. You can use it in any place where `Skin` is needed.
```java
FileHandle skinFile = Gdx.files.internal("assets/uiskin.json");
VisUI.load(new FreeTypeSkin(skinFile));
```
