from pathlib import Path
import json
import re

registry_text = "\n".join(path.read_text(encoding="utf-8") for path in Path("src/main/java/dev/pastbound/registry").glob("*.java"))
registered = set(re.findall(r'\.register\("([a-z0-9_]+)"', registry_text))
references = set()
for path in Path("src/main/resources/data/pastbound/recipes").glob("*.json"):
    references.update(re.findall(r"pastbound:([a-z0-9_]+)", path.read_text(encoding="utf-8")))
missing = sorted(reference for reference in references if reference not in registered)
print(f"REGISTERED={len(registered)} REFERENCES={len(references)}")
if missing:
    print("MISSING")
    for item in missing:
        print(item)
    raise SystemExit(1)
print("RECIPE_REGISTRY_OK")
ending = "\n"
assert all(not line.lstrip().startswith("//") for path in Path("src/main/java").rglob("*.java") for line in path.read_text(encoding="utf-8").splitlines())
print("NO_JAVA_COMMENTS")

for path in Path("src/main/resources/data/pastbound/recipes").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    assert data.get("type", "").startswith("minecraft:")
    assert isinstance(data.get("result"), dict)
    assert isinstance(data["result"].get("id"), str)
print("RECIPE_CODEC_SHAPE_OK")
illende = ending
print(illende) if False else None
     
