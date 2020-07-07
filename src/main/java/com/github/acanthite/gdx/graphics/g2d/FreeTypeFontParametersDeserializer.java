package com.github.acanthite.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.Hinting;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Deserializes {@link FreeTypeFontParameter} . Specifically all parameters except
 * {@link FreeTypeFontParameter#packer} and {@link FreeTypeFontParameter#incremental}
 * @author acanthite
 */
class FreeTypeFontParametersDeserializer extends Json.ReadOnlySerializer<FreeTypeFontParameter> {

    @Override
    public FreeTypeFontParameter read(Json json, JsonValue jsonData, Class type) {
        FreeTypeFontParameter param = new FreeTypeFontParameter();

        param.size = json.readValue("size", int.class, jsonData);
        param.mono = json.readValue("mono", boolean.class, param.mono, jsonData);

        param.hinting = Hinting.valueOf(json.readValue("hinting", String.class, param.hinting.name(), jsonData));

        // skin should already have a serializer for Color, no need to use anything custom here
        param.color = json.readValue("color", Color.class, param.color, jsonData);
        param.gamma = json.readValue("gamma", float.class, param.gamma, jsonData);
        param.renderCount = json.readValue("renderCount", int.class, param.renderCount, jsonData);

        param.borderWidth = json.readValue("borderWidth", float.class, param.borderWidth, jsonData);
        param.borderColor = json.readValue("borderColor", Color.class, param.borderColor, jsonData);
        param.borderStraight = json.readValue("borderStraight", boolean.class, param.borderStraight, jsonData);
        param.borderGamma = json.readValue("borderGamma", float.class, param.borderGamma, jsonData);

        param.shadowOffsetX = json.readValue("shadowOffsetX", int.class, param.shadowOffsetX, jsonData);
        param.shadowOffsetY = json.readValue("shadowOffsetY", int.class, param.shadowOffsetY, jsonData);
        param.shadowColor = json.readValue("shadowColor", Color.class, param.shadowColor, jsonData);

        param.spaceX = json.readValue("spaceX", int.class, param.spaceX, jsonData);
        param.spaceY = json.readValue("spaceY", int.class, param.spaceY, jsonData);

        param.padTop = json.readValue("padTop", int.class, param.padTop, jsonData);
        param.padLeft = json.readValue("padLeft", int.class, param.padLeft, jsonData);
        param.padBottom = json.readValue("padBottom", int.class, param.padBottom, jsonData);
        param.padRight = json.readValue("padRight", int.class, param.padRight, jsonData);

        param.characters = json.readValue("characters", String.class, param.characters, jsonData);

        param.kerning = json.readValue("kerning", boolean.class, param.kerning, jsonData);

        param.flip = json.readValue("flip", boolean.class, param.flip, jsonData);
        param.genMipMaps = json.readValue("genMipMaps", boolean.class, param.genMipMaps, jsonData);

        param.magFilter = TextureFilter.valueOf(json.readValue("magFilter", String.class, param.magFilter.name(), jsonData));
        param.minFilter = TextureFilter.valueOf(json.readValue("minFilter", String.class, param.minFilter.name(), jsonData));

        return param;
    }
}
