package testconfigs.testdata;

import utils.MapUtil;

import java.util.*;

public class CropHints {

    private CropHints() {
    }

    private static List<String> k = new ArrayList<>(Arrays.asList("en", "ua", "ru", "pl", "de"));
    private static List<String> imgNotDistHint = new ArrayList<>(Arrays.asList(
            "The image will not be distorted.",
            "Зображення не буде спотворене.",
            "Изображение не будет искажено.",
            "Obraz nie zostanie zniekształcony.",
            "Das Bild wird nicht verzerrt."));

    private static List<String> imgWillBeDistHint = new ArrayList<>(Arrays.asList(
            "This image could be distorted, please, crop the picture.",
            "Зображення може бути спотворене у повідомленні, необхідно обрізати картинку.",
            "Изображение может быть искажено в сообщении, необходимо обрезать картинку.",
            "Ten obraz może być zniekształcony, proszę, przytnij obrazek.",
            "Dieses Bild könnte verzerrt sein, bitte schneide es."
    ));

    private static List<String> imgWillZoomHint = new ArrayList<>(Arrays.asList(
            "The image will be zoomed out, but not distorted.",
            "Зображення буде зменшене, але не буде спотворене.",
            "Изображение будет уменьшено, но не будет искажено.",
            "Obraz zostanie pomniejszony, ale nie będzie zniekształcony.",
            "Das Bild wird herausgezoomt, aber nicht verzerrt."
            ));

    public static Map<String, String> getImgNotDistHint() {
        return MapUtil.toMap(k, imgNotDistHint);
    }

    public static Map<String, String> getImgWillBeDistHint() {
        return MapUtil.toMap(k, imgWillBeDistHint);
    }

    public static Map<String, String> getImgWillZoomHint(){
        return MapUtil.toMap(k, imgWillZoomHint);
    }

    public static void main(String[] args) {
        Map<String, String> v = getImgNotDistHint();
        Map<String, String> v1 = getImgWillBeDistHint();
        Map<String, String> v2 = getImgWillZoomHint();

        v.forEach((k, val) -> System.out.println("KEY: " + k + " VAL " + val));
        v1.forEach((k, val) -> System.out.println("KEY: " + k + " VAL " + val));
        v2.forEach((k, val) -> System.out.println("KEY: " + k + " VAL " + val));
    }


}
