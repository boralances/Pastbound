from pathlib import Path
from zipfile import ZIP_DEFLATED, ZipFile

root = Path(__file__).resolve().parents[1]
classes = root / "build/classes/java/main"
resources = root / "src/main/resources"
output = root / "build/libs" / "Pastbound-2.5.3.jar"
properties = {
    "mod_id": "pastbound",
    "mod_name": "Pastbound",
    "mod_license": "MIT",
    "mod_version": "2.5.3",
    "minecraft_version": "26.2",
    "minecraft_version_range": "[26.2]",
    "neo_version": "26.2.0.66",
    "curios_version": "16.0.0+26.2",
}

def expand_metadata(text):
    for key, value in properties.items():
        text = text.replace("${" + key + "}", value)
    return text

files = {}
for base in (classes, resources):
    if not base.exists():
        continue
    for path in base.rglob("*"):
        if path.is_file():
            relative = path.relative_to(base).as_posix()
            data = path.read_bytes()
            if relative == "META-INF/neoforge.mods.toml":
                data = expand_metadata(data.decode("utf-8")).encode("utf-8")
            files[relative] = data

output.parent.mkdir(parents=True, exist_ok=True)
with ZipFile(output, "w", compression=ZIP_DEFLATED, compresslevel=9) as archive:
    for relative in sorted(files):
        archive.writestr(relative, files[relative])
print(f"JAR_READY {output} {output.stat().st_size} bytes {len(files)} files")
