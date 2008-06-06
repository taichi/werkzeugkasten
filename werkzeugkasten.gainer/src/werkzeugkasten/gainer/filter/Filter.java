package werkzeugkasten.gainer.filter;

import werkzeugkasten.gainer.AinEventListener;

/**
 * アナログ入力をフィルタリングする
 * 
 * @author taichi
 */
public interface Filter extends AinEventListener {

	String name();
}
