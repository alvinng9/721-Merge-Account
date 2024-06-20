import java.util.*;

public class accountsMerge {
    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new String[]{"John","johnsmith@mail.com","john_newyork@mail.com"})));
        input.add(new ArrayList<>(Arrays.asList(new String[]{"John","johnsmith@mail.com","john00@mail.com"})));
        input.add(new ArrayList<>(Arrays.asList(new String[]{"John","johnnybravo@mail.com"})));
        input.add(new ArrayList<>(Arrays.asList(new String[]{"Mary","mary@mail.com"})));
        List<List<String>> output = accountsMerge(input);
        System.out.println(output);
    }


    static class UnionFind {
        int[] parent;
        int[] weight;

        public UnionFind(int num) {
            parent = new int[num];
            weight = new int[num];

            for (int i = 0; i < num; i++) {
                parent[i] = i;
                weight[i] = 1;
            }
        }

        public int find(int a) {
            if (parent[a] == a) {
                return a;
            }
            return find(parent[a]);
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return;
            }

            if (weight[rootA] > weight[rootB]) {
                weight[rootA] += weight[rootB];
                parent[rootB] = rootA;
            } else {
                weight[rootB] += weight[rootA];
                parent[rootA] = rootB;
            }
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int size = accounts.size();
        UnionFind uf = new UnionFind(size);
        HashMap<String, Integer> emailToIds = new HashMap<>();
        for (int i = 0; i < size; i++) {
            List<String> emails = accounts.get(i);
            for (int j = 1; j < emails.size(); j++) {
                String email = emails.get(j);
                if (!emailToIds.containsKey(email)) {
                    emailToIds.put(email, i);
                }
                uf.union(i, emailToIds.get(email));
            }
        }

        HashMap<Integer, List<String>> idToEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToIds.entrySet()) {
            int name = uf.find(entry.getValue());
            if (!idToEmails.containsKey(name)) {
                idToEmails.put(name, new ArrayList<String>());
            }
            List<String> temp = idToEmails.get(name);
            temp.add(entry.getKey());
            idToEmails.put(name, temp);
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : idToEmails.entrySet()) {
            String name = accounts.get(entry.getKey()).get(0);
            List<String> emails = entry.getValue();
            emails.sort(String::compareTo);
            List<String> temp = new ArrayList<>();
            temp.add(name);
            temp.addAll(emails);
            result.add(temp);
        }
        return result;
    }
}
