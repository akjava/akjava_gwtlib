akjava.com GWT Lib
==================
`Apache 2.0 License`

all my project use this libraly.  
gwthtml5 is merging this.

Developing Concept
-------

as possible as replace with newer Guava

How to use
-----------

add newest libraly name ends with -dep(dep contain stable Guava)  
add this line at your module.xml
```
<inherits name='com.akjava.gwt.common.Common'/>
```

Package summary
-------

basically something chaos..

- com.akjava.gwt.common - newest packages for GWT,I'm trying redesign here.
- com.akjava.gwt.lib.client - for GWT someof code use Guava
- com.akjava.lib.common - maybe work on pure-java but newest Guava needed.


###com.akjava.gwt.common.form
something trying integrate GWT + normal CGI

###com.akjava.gwt.lib.client
this is oldest package and really chaos.
###com.akjava.gwt.lib.client.canvas
controling Canvas classes,however somt other similar code are not here.
###com.akjava.gwt.lib.client.datalist
DataList is somekind of simple database lib with HTML5 WebStorage  
iOS does not support indexedDB,i'm have to use that.

###com.akjava.gwt.lib.client.datalist.functions
classes implements Guavas's Function for datalist
###com.akjava.gwt.lib.client.entries
classed extends EntryPoint
###com.akjava.gwt.lib.client.game
lib for game,solve maze.
###com.akjava.gwt.lib.client.io
extend io 
###com.akjava.gwt.lib.client.storage
more specific web storage api
###com.akjava.gwt.lib.client.widget
my custom widget

###com.akjava.gwt.lib.client.widget.cell
my custom cells & help create cells easily.

###com.akjava.gwt.lib.client.widget.cell.util
more specific help tools for cells

###com.akjava.lib.common.csv
csv reader & writer,but here is chaos

###com.akjava.lib.common.form
Form is create form-input-html-tag by csv

###com.akjava.lib.common.form.page
lib for paging

###com.akjava.lib.common.functions
classes implements Guavas's Function for Java

###com.akjava.lib.common.io
extend IO

###com.akjava.lib.common.param
parameter?

###com.akjava.lib.common.predicates
predicates for java

###om.akjava.lib.common.repackaged.org.apache.xmlgraphics.util
org.apache.xmlgraphics.util

###com.akjava.lib.common.tag
simple control html tag

###com.akjava.lib.common.utils
static classes

###com.akjava.lib.common.utils.log
help logging

Project
-------
- src - sourcecode
- lib - dependency libs
- build - released jar
- build.xml - ant build file (need GWT 2.6.1)