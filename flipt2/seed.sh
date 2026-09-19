#!/bin/sh
# Seeds flipt2/data: a bare git repo that the Flipt 2.0 "local" storage
# backend manages directly (every flag/segment change, whether from this
# script or the Flipt UI, is a real git commit in this repo).
#
# Only runs on first setup (i.e. if flipt2/data doesn't exist yet), so it
# never clobbers changes made later through the Flipt UI. Delete flipt2/data
# to force a fresh reseed from flipt2/seed/.
set -e

cd "$(dirname "$0")"

if [ -d data ]; then
  echo "flipt2/data already exists, skipping seed (delete it to reseed from flipt2/seed/)"
  exit 0
fi

git init --bare data >/dev/null

tmp=$(mktemp -d)
git clone -q data "$tmp"
cp -R seed/. "$tmp/"
git -C "$tmp" checkout -q -b main
git -C "$tmp" add -A
git -C "$tmp" -c user.name=flipt2-seed -c user.email=flipt2-seed@localhost commit -q -m "Seed initial flags and segments"
git -C "$tmp" push -q origin main

# Flipt's local backend reads from refs/remotes/origin/main in the data repo
# itself (not refs/heads/main - that's just an artifact of pushing via a clone).
commit=$(git -C "$tmp" rev-parse main)
git -C data update-ref refs/remotes/origin/main "$commit"
git -C data update-ref -d refs/heads/main

rm -rf "$tmp"

echo "Seeded flipt2/data from flipt2/seed/ on branch 'main'"