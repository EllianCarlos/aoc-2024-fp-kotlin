{
  description = "aoc-2024-fp-kotlin";

  inputs = { nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable"; };

  inputs.systems.url = "github:nix-systems/default";

  inputs.flake-utils.url = "github:numtide/flake-utils";
  inputs.flake-utils.inputs.systems.follows = "systems";

  outputs = { self, systems, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs { inherit system; };
      in {
        devShells.default = pkgs.mkShell {
          buildInputs = [
            pkgs.gradle_8
            pkgs.ktlint
            pkgs.kotlin
            pkgs.jdk21
          ];
        };
        formatter = nixpkgs.legacyPackages.x86_64-linux.nixfmt-rfc-style;
      });
}
