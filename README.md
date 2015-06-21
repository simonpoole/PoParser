# PoParser

This is a very simplistic parser for .po files. Mainly quickly hacked in so that we can translate preset names for https://github.com/MarcusWolschon/osmeditor4android and https://github.com/simonpoole/beautified-JOSM-preset . It currently ignores plurals and probably should be fixed to not do so.

## Usage

    Po p = new Po(_inputstreamfrompofile_);
    String translated = p.t(_originalstring_);
    
    or
    
    String translated = p.t(_context_, _originalstring_);
    
## Background

Android has its own i8n system that works quite well, however it fails if you need to translate text that is not programmatically used. The specific use case that prompted writing this parser is the translation of preset names for the mobile OpenStreetMap editor [Vespucci](https://github.com/MarcusWolschon/osmeditor4android). The presets are defined ia a XML file and using Android resource ids would have been extremely painful. Using a system that use the untranslated string as key is clearly far more attractive. 

For whatever reason there is no freely available support for .po files on Android, or matter of fact any -reasonable- support for Java at all, which prompted the quick hacking of this parser. 
