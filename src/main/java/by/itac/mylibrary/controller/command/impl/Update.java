package by.itac.mylibrary.controller.command.impl;

import by.itac.mylibrary.controller.command.Command;
import by.itac.mylibrary.entity.Book;
import by.itac.mylibrary.service.BookService;
import by.itac.mylibrary.service.ServiceProvider;
import by.itac.mylibrary.service.exception.ServiceException;

public class Update implements Command{

	@Override
	public String execute(String request) {
		
	    final int idIndex = 1;
	    final int authorIndex = 2;
	    final int titleIndex = 3;
	    final int yearIndex = 4;

		final String paramDelimeter = " ";

		String[] param = request.split(paramDelimeter);

		String response = null;

		Book book = new Book(Integer.parseInt(param[idIndex]), param[authorIndex], param[titleIndex], Integer.parseInt(param[yearIndex]));
        int id = Integer.parseInt(param[idIndex]);
		ServiceProvider serviceProvider = ServiceProvider.getInstance();

		BookService bookService = serviceProvider.getBookService();
		try {
			bookService.update(id, book);
			response = "Book is updated successfully";
		} catch (ServiceException e) {
			response = "Error during book updating";
		}
		return response;
	}

}
