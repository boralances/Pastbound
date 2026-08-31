package dev.pastbound.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class PastboundConfig {
    private static final Gson JSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String DOSYA_ADI = "pastbound-client.json";
    private static boolean wikiGiris = true;
    private static boolean gorevIsaretleri = true;
    private static boolean olayDuyurulari = true;
    private static boolean yankilar = true;
    private PastboundConfig() {
    }
    public static void yukle() {
        Path dosya = dosya();
        if (!Files.exists(dosya)) {
            kaydet();
            return;
        }
        try (Reader okuyucu = Files.newBufferedReader(dosya)) {
            JsonObject nesne = JSON.fromJson(okuyucu, JsonObject.class);
            if (nesne == null) return;
            wikiGiris = oku(nesne, "wikiGiris", wikiGiris);
            gorevIsaretleri = oku(nesne, "gorevIsaretleri", gorevIsaretleri);
            olayDuyurulari = oku(nesne, "olayDuyurulari", olayDuyurulari);
            yankilar = oku(nesne, "yankilar", yankilar);
        } catch (Exception ignored) {
            kaydet();
        }
    }
    public static void kaydet() {
        try {
            Path dosya = dosya();
            Files.createDirectories(dosya.getParent());
            JsonObject nesne = new JsonObject();
            nesne.addProperty("wikiGiris", wikiGiris);
            nesne.addProperty("gorevIsaretleri", gorevIsaretleri);
            nesne.addProperty("olayDuyurulari", olayDuyurulari);
            nesne.addProperty("yankilar", yankilar);
            try (Writer yazici = Files.newBufferedWriter(dosya)) {
                JSON.toJson(nesne, yazici);
            }
        } catch (Exception ignored) {
        }
    }
    private static Path dosya() {
        return Minecraft.getInstance().gameDirectory.toPath().resolve("config").resolve(DOSYA_ADI);
    }
    private static boolean oku(JsonObject nesne, String anahtar, boolean varsayilan) {
        return nesne.has(anahtar) && nesne.get(anahtar).isJsonPrimitive() ? nesne.get(anahtar).getAsBoolean() : varsayilan;
    }
    public static boolean wikiGiristeAcilsin() { return wikiGiris; }
    public static boolean gorevIsaretleriAcik() { return gorevIsaretleri; }
    public static boolean olayDuyurulariAcik() { return olayDuyurulari; }
    public static boolean yankilarAcik() { return yankilar; }
    public static void wikiGiristeAcilsin(boolean deger) { wikiGiris = deger; }
    public static void gorevIsaretleriAcik(boolean deger) { gorevIsaretleri = deger; }
    public static void olayDuyurulariAcik(boolean deger) { olayDuyurulari = deger; }
    public static void yankilarAcik(boolean deger) { yankilar = deger; }
}
