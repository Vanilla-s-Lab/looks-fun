{ pkgs ? import <nixpkgs> { } }:
(pkgs.buildFHSUserEnv {
  name = "looks-fun";

  targetPkgs = pkgs: with pkgs;
    [ gradle ];

  runScript = "bash";
}).env
