import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound"
BLOCK_MODELS = ROOT / "models/block"
ITEM_MODELS = ROOT / "models/item"
ITEM_DEFS = ROOT / "items"


def write_json(path: Path, payload: dict) -> None:
    path.write_text(json.dumps(payload, indent=2, ensure_ascii=False) + "\n")

for wood in ("chinampa_cypress", "uruk_cedar"):
    write_json(
        BLOCK_MODELS / f"{wood}_fence_gate_open_wall.json",
        {
            "parent": "minecraft:block/template_fence_gate_wall_open",
            "textures": {"texture": f"pastbound:block/{wood}_fence_gate"},
        },
    )

for name, parent in (
    ("nether_wart_trapdoor_open", "minecraft:block/template_trapdoor_open"),
    ("nether_wart_trapdoor_top", "minecraft:block/template_trapdoor_top"),
    ("nether_wart_trapdoor_top_open", "minecraft:block/template_trapdoor_top_open"),
):
    write_json(
        BLOCK_MODELS / f"{name}.json",
        {"parent": parent, "textures": {"texture": "pastbound:block/nether_wart_trapdoor"}},
    )

for door in ("chinampa_cypress_door", "nether_wart_door"):
    write_json(
        ITEM_MODELS / f"{door}.json",
        {"parent": "minecraft:item/generated", "textures": {"layer0": f"pastbound:block/{door}"}},
    )
    write_json(
        ITEM_DEFS / f"{door}.json",
        {"model": {"type": "minecraft:model", "model": f"pastbound:item/{door}"}},
    )

write_json(
    ITEM_DEFS / "uruk_cedar_door.json",
    {"model": {"type": "minecraft:model", "model": "pastbound:item/uruk_cedar_door"}},
)
