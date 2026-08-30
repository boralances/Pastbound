Curios 16.x API incelemesi 2026-08-30.

Kaynaklar:
- https://github.com/TheIllusiveC4/Curios/wiki/How-to-Use-Curios
- https://github.com/TheIllusiveC4/Curios/discussions/432

Doğrulanan bulgular:
- Curios item uygunluğu slot identifier’a karşılık gelen item tag dosyalarıyla tanımlanabiliyor; örnek yol data/curios/tags/items/<slot_id>.json.
- Curios API cache içindeki ISlotType; getIdentifier, getSize, getOrder, getIcon, getValidators ve isItemValid gibi slot tipi bilgileri taşıyor.
- ICuriosSlots service; getSlotTypes, getSlotData ve registerPredicate API’lerini sunuyor.
- Projede RelikItem zaten ICurioItem kullanıyor ve RelikMantigi CuriosApi.getCuriosInventoryOrNull ile relic slotlarını okuyor.
- Resmi Curios wiki, geliştirici ve slot modifier dokümantasyonunun mevcut olduğunu gösteriyor; özel slot uygulaması için sürümün datapack/config şemasının proje cache’i ve derleme ile doğrulanması gerekiyor.
