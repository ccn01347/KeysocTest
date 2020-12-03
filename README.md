# KeysocTest
 
This is a demo project for code testing on Keysoc.

### Design Pattern
- MVVM

#### Notice
- The bookmark is only Temporary save on memory. If wanna change to that on storage, please use Database as well.
- Class KSAlumsBookmarkViewModel can be also inherited by KSAlbum. However, in this demo I used component structure instead.
- AlbumsRecylerAdapter can also include DiffUtil class to implement the update of data, like BookmarksRecylerAdapter.


## Features
- API request to search the album information for "Jack Johnson"
- Use RecylerView instead of ListView.
- Temporary bookmark.
- Swipe left delete bookmark.


Edited by Steve Lai
