from pathlib import Path
import re
import subprocess

root = Path(__file__).resolve().parents[1]
result = subprocess.run(["gh", "release", "list", "--limit", "100", "--json", "tagName", "--template", "{{range .}}{{.tagName}}{{\"\\n\"}}{{end}}"], cwd=root, check=True, capture_output=True, text=True)
tags = [x.strip() for x in result.stdout.splitlines() if x.strip()]
patterns = [
    r"(?ims)^##\s*(?:validation|kontrol|doğrulama|verification|tests?).*?(?=^##\s|\Z)",
    r"(?ims)^\|\s*(?:control|kontrol|validation|test|sha-256|jar integrity|derleme|compile).*?(?=^\n\s*\n|^##\s|\Z)",
    r"(?im)^\*\*(?:validation|kontrol|doğrulama|test|sha-256|jar integrity|derleme|compile).*?$",
]
for tag in tags:
    body = subprocess.run(["gh", "release", "view", tag, "--json", "body", "--template", "{{.body}}"], cwd=root, check=True, capture_output=True, text=True).stdout
    cleaned = body
    for pattern in patterns:
        cleaned = re.sub(pattern, "", cleaned)
    cleaned = re.sub(r"\n{3,}", "\n\n", cleaned).strip() + "\n"
    if cleaned != body:
        subprocess.run(["gh", "release", "edit", tag, "--notes", cleaned], cwd=root, check=True)
        print(f"cleaned {tag}")
    else:
        print(f"unchanged {tag}")
print(f"processed {len(tags)} releases")

for path in root.glob("release-notes-*.md"):
    text = path.read_text(encoding="utf-8")
    cleaned = re.sub(patterns[0], "", text)
    cleaned = re.sub(patterns[1], "", cleaned)
    cleaned = re.sub(patterns[2], "", cleaned)
    cleaned = re.sub(r"\n{3,}", "\n\n", cleaned).strip() + "\n"
    path.write_text(cleaned, encoding="utf-8")
    print(f"cleaned file {path.name}")

print("release_notes_cleaned")

