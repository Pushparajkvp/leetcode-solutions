/*
Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

 

Example:

Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]

Output:
[null,[],null,null,["a"],"hello"]

Explanation:
filesystem
 

Note:

You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
*/
class FileSystem {
    
    TreeMap<String, Object> root;
    
    public FileSystem() {
         root = new TreeMap<String, Object>();
    }
    
    public List<String> ls(String path) {
        String[] paths  = path.split("/");
        Object trav = root;
        
        for(int it=1; it<paths.length; it++) {
            trav = ((TreeMap<String, Object>)trav).get(paths[it]);
        }
        
        ArrayList<String> result = new ArrayList<>();
        
        if(trav instanceof TreeMap) {
            for(String str : ((TreeMap<String, Object>)trav).keySet()) {
                result.add(str);
            }
        } else if(trav instanceof String){
            result.add(paths[paths.length-1]);
        }
        return result;
    }
    
    public void mkdir(String path) {
        String[] paths  = path.split("/");
        TreeMap<String, Object> trav = root;
        
        for(int it=1; it<paths.length; it++) {
            if(trav.containsKey(paths[it])) {
                trav = (TreeMap<String, Object>)trav.get(paths[it]); 
            } else {
                TreeMap<String, Object> newDir = new TreeMap<String, Object>();
                trav.put(paths[it], newDir);
                trav = newDir;
            }
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        String[] paths  = filePath.split("/");
        TreeMap<String, Object> trav = root;
        
        for(int it=1; it<paths.length - 1; it++) {
            if(trav.containsKey(paths[it])) {
                trav = (TreeMap<String, Object>)trav.get(paths[it]); 
            } else {
                TreeMap<String, Object> newDir = new TreeMap<String, Object>();
                trav.put(paths[it], newDir);
                trav = newDir;
            }
        }
        String fileName = paths[paths.length - 1];
        if(trav.containsKey(fileName)) {
            String str = (String)trav.get(fileName);
            trav.put(fileName, str + content);
        } else {
            trav.put(fileName, content);
        }
    }
    
    public String readContentFromFile(String filePath) {
        String[] paths  = filePath.split("/");
        TreeMap<String, Object> trav = root;
        
        for(int it=1; it<paths.length - 1; it++) {
            if(trav.containsKey(paths[it])) {
                trav = (TreeMap<String, Object>)trav.get(paths[it]); 
            } else {
                return "";
            }
        }
        
        String fileName = paths[paths.length - 1];
        if(trav.containsKey(fileName)) {
            String str = (String)trav.get(fileName);
            return str;
        }
           
        return "";
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */