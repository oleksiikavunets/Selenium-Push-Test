package testconfigs.testdata;

import pageutils.MapUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CropRecommendations {

    private CropRecommendations() {
    }

    private static List<String> k = new ArrayList<>(Arrays.asList("en", "ua", "ru", "pl", "de"));

    private static List<String> iconCropRec = new ArrayList<>(Arrays.asList(
            "The minimum size of the picture is 80x80, the recommended 192x192. For pictures whose size exceeds 80x80, the aspect ratio should be 1 to 1. Supported formats: JPG, PNG, GIF — up to 200KB",
            "Мінімальний розмір картинки 80х80, рекомендований 192х192. Для картинок, розмір яких перевищує 80х80, співвідношення сторін повинно бути 1 до 1. Формати що підтримуються: JPG, PNG, GIF — до 200KB",
            "Минимальный размер картинки 80х80, рекомендуемый 192х192. Для картинок размер которых превышает 80х80, соотношение сторон должно быть 1 к 1. Поддерживаемые форматы: JPG, PNG, GIF — до 200KB",
            "Minimalny rozmiar obrazu to 80x80, zalecane 192x192. Dla zdjęć o rozmiarze przekraczającym 80x80, proporcje powinny wynosić 1 do 1. Obsługiwane formaty: JPG, PNG, GIF - do 200 KB",
            "Die Mindestgröße des Bildes beträgt  80x80, die empfohlene 192x192. Für Bilder, deren Größe 80x80 übersteigt, sollte das Seitenverhältnis 1 zu 1 sein. Unterstützte Formate: JPG, PNG, GIF - bis zu 200 KB"
    ));

    private static List<String> bigImgCropRec = new ArrayList<>(Arrays.asList(
            "The minimum size of the picture is 360x240. For pictures larger than 360x240, the aspect ratio should be 1.5 to 1. Supported formats: JPG, PNG, GIF — up to 200KB",
            "Мінімальний розмір картинки 360х240. Для картинок, розмір яких перевищує 360х240, співвідношення сторін повинно бути 1,5 до 1. Формати що підтримуються: JPG, PNG, GIF — до 200KB",
            "Минимальный размер картинки 360х240. Для картинок размер которых превышает 360х240, соотношение сторон должно быть 1.5 к 1. Поддерживаемые форматы: JPG, PNG, GIF — до 200KB",
            "Minimalny rozmiar zdjęcia to 360x240. W przypadku zdjęć większych niż 360x240 proporcje powinny wynosić od 1,5 do 1. Obsługiwane formaty: JPG, PNG, GIF - do 200 KB",
            "Die Mindestgröße des Bildes beträgt 360 x 240. Für Bilder die größer als 360 x 240 sind, sollte das Seitenverhältnis 1,5 zu 1 sein. Unterstützte Formate: JPG, PNG, GIF - bis zu 200 KB"));

    public static Map<String, String> getIconCropRecommendations() {
        return MapUtil.toMap(k, iconCropRec);
    }

    public static Map<String, String> getBigImgCropRecommendations() {
        return MapUtil.toMap(k, bigImgCropRec);
    }


}
