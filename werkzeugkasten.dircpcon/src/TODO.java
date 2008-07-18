public class TODO {

	// ルートディレクトリ直下にあるjarとzipを集めてclasspath
	// ファイル名とディレクトリ構成から類推して、sourcepath
	// XXX 一度クラスパスコンテナに取り込まれてしまったjarをクラスパスコンテナから外す方法がない。
	// XXX ディレクトリのコンテキストメニューから新しいDirClasspathContainer
	// XXX projectのプロパティから、ディレクトリ一覧を編集
	// XXX VARIABLEをresolveして、そのディレクトリにあるjarとzipをclasspath
	// XXX 現在のdircpContainerを、通常のclasspathにexport
	// XXX dirclasspathcontainerのコンテキストメニューから通常のclasspathにexport
}
