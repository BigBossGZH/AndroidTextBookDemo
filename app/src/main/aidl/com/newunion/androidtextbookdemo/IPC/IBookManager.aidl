// Book.aidl
package com.newunion.androidtextbookdemo.IPC;

// Declare any non-default types here with import statements

import com.newunion.androidtextbookdemo.IPC.Book;
interface IBookManager {
  List<Book> getBookList();
  void addBook(in Book book);
}
