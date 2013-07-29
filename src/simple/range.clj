;; The contents of this file are subject to the LGPL License, Version 3.0.
;;
;; Copyright (C) 2013, Phillip Lord, Newcastle University
;;
;; This program is free software: you can redistribute it and/or modify it
;; under the terms of the GNU Lesser General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or (at your
;; option) any later version.
;;
;; This program is distributed in the hope that it will be useful, but WITHOUT
;; ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
;; FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
;; for more details.
;;
;; You should have received a copy of the GNU Lesser General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.

(ns simple.range
  (:refer-clojure :only [])
  (:use [tawny.owl])
  (:require [tawny.reasoner :as r]))

;; ## Introduction
;;
;; This demonstrates the inferences that can be made as a result of range
;; restrictions on a property.

;; ## Preamble
;;
;; Define the ontology and set the reasoner factory up.
(defontology range)
(r/reasoner-factory :hermit)

;; ## Main Entities
;;
;; We define a single class `X` and a property `r` which has `X` as its range.
(defclass X)
(defoproperty r
  :range X)

;; Now we define two individuals, `i` and `j` there is `r` relationship
;; between `j` and `i`.
(defindividual i)
(defindividual j
  :fact (fact r i))

;; We also define two classes with an existential relationship between them.
(defclass A)
(defclass B
  :subclass (owlsome r A))


;; ## Inferences
;;
;; With the range characteristic on `r` we are stating that anything which is
;; related to something by `r` is an instance of `X`. Or alternatively, any
;; individual with an incoming `r` edge is an instance of `X`. Although `i`
;; has no definition for itself, it is used in the definition of `j` and this
;; affects its classification. `i` must be an instance of `X`.
(r/instances X)


;; If this were similar to domain, this would force a reclassification -- `A`
;; would have `X` as a superclass. But it doesn't, because while every `B` has
;; an outgoing `r` edge, we do not know whether every `A` has an incoming one
;; -- just the ones connected to a `B` instances.
(r/isuperclasses A)


;; ## Conclusions
;;
;; The range restriction states what kind of entity must be at the incoming
;; end of a relationship, where ever that relationship is used. As with `only`
;; restrictions, this can give somewhat counter-intuitive inferences, as a
;; relationship is infered between two entities (`X` and `i`) with no
;; definitions or frames themselves.

;; ## See Also
;;
;; - [domain][#simple.domain] for inferences that can be made as a result of a
;;   `domain` statement.
;; - [only-individual][#simple.only-individual) which describes how the `only`
;;   restriction, which also affects the entity related to.
