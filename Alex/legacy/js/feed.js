$.jGFeed('http://feeds.feedburner.com/jQueryHowto', function(feeds) {
	if(!feeds) {
		return false;	
	}
	for(var i=0; i<feeds.entries.length; i++) {
		var entry = feeds.entries[i];
		entry.title;
	}
}, 10);
