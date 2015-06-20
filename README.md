# PoParser

This is a very simplistic parser for .po files. Mainly quickly hacked in so that we can translate preset names for https://github.com/MarcusWolschon/osmeditor4android and https://github.com/simonpoole/beautified-JOSM-preset . It currently ignores plurals and probably should be fixed to do so.

Usage

    Po p = new Po(_inputstreamfrompofile_);
    String translated = p.t(_originalstring_);
    
    or
    
    String translated = p.t(_context_, _originalstring_);
