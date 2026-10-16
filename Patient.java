class Patient {
    public static void main(String[] args) {
        try {
            int severity = 6;

            if (severity < 1 || severity > 5) {
                throw new IllegalArgumentException("Invalid severity");
            }

            System.out.println("Patient registered");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}