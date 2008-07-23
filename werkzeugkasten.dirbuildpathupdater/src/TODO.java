public class TODO {

	// ルートディレクトリ直下にあるjarとzipを集めてclasspath
	// ファイル名とディレクトリ構成から類推して、sourcepath
	// 一度クラスパスコンテナに取り込まれてしまったjarをクラスパスコンテナから外す方法がない。
	// ディレクトリのコンテキストメニューから新しいDirClasspathContainer
	// XXX projectのプロパティから、ディレクトリ一覧を編集
	// XXX VARIABLEをresolveして、そのディレクトリにあるjarとzipをclasspath
	// 現在のdircpContainerを、通常のclasspathにexport
	// dirclasspathcontainerのコンテキストメニューから通常のclasspathにexport
	// XXX build pathとして使用しているディレクトリのラベルを弄る。
}
