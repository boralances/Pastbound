from pathlib import Path
import json

root = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound/lang"
text = "This block preserves the historical scene."
mesajlar = {
    "message.pastbound.scene.pillar_locked": "Mine the required steel ore first, then activate the Resonance Pillar.",
    "message.pastbound.scene.pillar_active": "The Resonance Pillar is active. Return to the time machine.",
}
for path in root.glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    data.setdefault("message.pastbound.scene.protected_block", text)
    data.update({key: data.get(key, value) for key, value in mesajlar.items()})
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print(f"PROTECTED_BLOCK_LOCALE_OK={len(list(root.glob('*.json')))}")
