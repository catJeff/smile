/*******************************************************************************
 * Copyright (c) 2010-2020 Haifeng Li. All rights reserved.
 *
 * Smile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * Smile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Smile.  If not, see <https://www.gnu.org/licenses/>.
 ******************************************************************************/

package smile.demo.manifold;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import smile.plot.swing.Canvas;
import smile.manifold.LLE;
import smile.plot.swing.Wireframe;

/**
 *
 * @author Haifeng Li. All rights reserved.
 */
@SuppressWarnings("serial")
public class LLEDemo extends ManifoldDemo {

    public LLEDemo() {
    }

    @Override
    public JComponent learn() {
        JPanel pane = new JPanel(new GridLayout(1, 2));
        double[][] data = dataset[datasetIndex].toArray();
        if (data.length > 1000) {
            double[][] x = new double[1000][];
            for (int i = 0; i < 1000; i++)
                x[i] = data[i];
            data = x;
        }

        long clock = System.currentTimeMillis();
        LLE lle = LLE.of(data, k);
        System.out.format("Learn LLE from %d samples in %dms\n", data.length, System.currentTimeMillis() - clock);

        double[][] vertices = lle.coordinates;
        int[][] edges = lle.graph.getEdges().stream().map(edge -> new int[]{edge.v1, edge.v2}).toArray(int[][]::new);

        Canvas plot = Wireframe.of(vertices, edges).canvas();
        plot.setTitle("LLE");
        pane.add(plot.panel());

        return pane;
    }

    @Override
    public String toString() {
        return "Locally Linear Embedding";
    }

    public static void main(String argv[]) {
        LLEDemo demo = new LLEDemo();
        JFrame f = new JFrame("Locally Linear Embedding");
        f.setSize(new Dimension(1000, 1000));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(demo);
        f.setVisible(true);
    }
}
