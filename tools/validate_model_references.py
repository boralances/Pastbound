import json
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/pastbound"
missing = []
checked = 0

for path in sorted((ASSETS / "blockstates").glob("*.json")):
    data = json.loads(path.read_text())
    for ref in re.findall(r'"model"\s*:\s*"pastbound:([^"]+)"', json.dumps(data)):
        checked += 1
        target = ASSETS / "models" / f"{ref}.json"
        if not target.exists():
            missing.append(f"{path.relative_to(ROOT)} -> {ref}")

for path in sorted((ASSETS / "items").glob("*.json")):
    data = json.loads(path.read_text())
    for ref in re.findall(r'"model"\s*:\s*"pastbound:([^"]+)"', json.dumps(data)):
        checked += 1
        target = ASSETS / "models" / f"{ref}.json"
        if not target.exists():
            missing.append(f"{path.relative_to(ROOT)} -> {ref}")

pack_meta = ROOT / "src/main/resources/pack.mcmeta"
if not pack_meta.exists():
    missing.append("src/main/resources/pack.mcmeta")
else:
    json.loads(pack_meta.read_text())

if missing:
    print("MISSING_OR_INVALID")
    print("\n".join(missing))
    raise SystemExit(1)
print(f"MODEL_REFERENCES_OK checked={checked}")
print("PACK_METADATA_OK")
