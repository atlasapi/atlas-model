package org.uriplay.media.entity.simple;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.uriplay.media.entity.simple.Playlist;
import org.uriplay.media.vocabulary.PLAY;

@XmlRootElement(namespace=PLAY.NS, name="uriplay")
@XmlType(name="uriplay", namespace=PLAY.NS)
public class UriplayXmlOutput extends Playlist {

}
