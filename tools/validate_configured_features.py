import json
from pathlib import Path

root = Path(__file__).resolve().parents[1] / "src/main/resources/data/pastbound/worldgen/configured_feature"
files = sorted(root.glob("*.json"))
for path in files:
    data = json.loads(path.read_text())
    if not isinstance(data.get("type"), str):
        raise SystemExit(f"{path}: missing string type")
    if "config" not in data or not isinstance(data["config"], dict):
        raise SystemExit(f"{path}: missing object config")
print(f"CONFIGURED_FEATURES_OK count={len(files)}")
