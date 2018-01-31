// Book.aidl
package com.newunion.androidtextbookdemo.IPC;

// Declare any non-default types here with import statements

import com.newunion.androidtextbookdemo.IPC.Book;
import com.newunion.androidtextbookdemo.IPC.IOnNewBookArrivedListener;
interface IBookManager {
  List<Book> getBookList();
  void addBook(in Book book);
  void registerListener(IOnNewBookArrivedListener listener);
  void unregisterListener(IOnNewBookArrivedListener listener);
}
